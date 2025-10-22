#include <iostream>
#include <sstream>
#include <fstream>
#include <cmath>
#include <string>
#include "functions.h"

//helpers
static inline int clampi(int v, int lo, int hi) {
    if (v < lo) return lo;
    if (v > hi) return hi;
    return v;
}
static inline double clampd(double v, double lo, double hi) {
    if (v < lo) return lo;
    if (v > hi) return hi;
    return v;
}
static inline double cubic(double p0, double p1, double p2, double p3, double t) {
    // Mitchell–Netravali cubic (B = 1/3, C = 1/3)
    constexpr double B = 1.0/3.0;
    constexpr double C = 1.0/3.0;

    auto w = [](double x, double B, double C) -> double {
        x = std::abs(x);
        const double x2 = x*x, x3 = x2*x;
        if (x < 1.0) {
            return ((12 - 9*B - 6*C)*x3 + (-18 + 12*B + 6*C)*x2 + (6 - 2*B)) / 6.0;
        } else if (x < 2.0) {
            return ((-B - 6*C)*x3 + (6*B + 30*C)*x2 + (-12*B - 48*C)*x + (8*B + 24*C)) / 6.0;
        } else {
            return 0.0;
        }
    };

    // samples at -1,0,1,2 with param t in [0,1] between p1 and p2
    return p0 * w(1.0 + t, B, C)
         + p1 * w(t,       B, C)
         + p2 * w(1.0 - t, B, C)
         + p3 * w(2.0 - t, B, C);
}

static inline uint8_t round_u8(double x) {
  double v = x;
  if (v < 0.0) v = 0.0;
  if (v > 255.0) v = 255.0;
  int r = static_cast<int>(std::floor(v + 0.5));  
  if (r < 0) r = 0;
  if (r > 255) r = 255;
  return static_cast<uint8_t>(r);
}
static inline Pixel get_clamped(const Image& img, int x, int y) {
    const int W = static_cast<int>(img.size());
    const int H = static_cast<int>(img.empty() ? 0 : img[0].size());
    x = clampi(x, 0, W - 1);
    y = clampi(y, 0, H - 1);
    return img[x][y];                 // Image is column-major: img[x][y]
}
Image load_image(const std::string& filename) {
    // TODO(student): implement image loading
        // --- validation of filename / stream ---
    if (filename.empty()) {
        throw std::runtime_error("Invalid filename");
    }
    std::ifstream in(filename);
    if (!in) {
        throw std::runtime_error(std::string("Failed to open ") + filename);
    }

    // --- header: type ---
    std::string magic;
    if (!(in >> magic)) {
        throw std::runtime_error("Failed to read type");
    }
    if (magic != "P3" && magic != "p3") {
        throw std::runtime_error(std::string("Invalid type ") + magic);
    }

    // helper to skip PPM comments
    auto skip_comments = [&in]() {
        while (in.peek() == '#') {
            std::string dummy;
            std::getline(in, dummy);
        }
    };

    // --- dimensions ---
    skip_comments();
    long long W, H;
    if (!(in >> W >> H)) {
        throw std::runtime_error("Invalid dimensions");
    }
    if (W <= 0 || H <= 0 ||
        W > static_cast<long long>(MAX_WIDTH) ||
        H > static_cast<long long>(MAX_HEIGHT)) {
        throw std::runtime_error("Invalid dimensions");
    }

    // --- max color ---
    skip_comments();
    long long maxc;
    if (!(in >> maxc)) {
        throw std::runtime_error("Invalid max color");
    }
    if (maxc < 1 || maxc > 255) {
        throw std::runtime_error("Invalid max color");
    }

    // --- pixel data (row-major in file) -> column-major in memory ---
    Image img(static_cast<size_t>(W), std::vector<Pixel>(static_cast<size_t>(H)));

    auto scale_to_255 = [maxc](long long v) -> uint8_t {
        if (maxc == 255) return static_cast<uint8_t>(v);
        double s = 255.0 * static_cast<double>(v) / static_cast<double>(maxc);
        // same half-up rounding we use everywhere
        int r = static_cast<int>(std::floor(s + 0.5));
        if (r < 0) r = 0; if (r > 255) r = 255;
        return static_cast<uint8_t>(r);
    };

    for (long long y = 0; y < H; ++y) {
        for (long long x = 0; x < W; ++x) {
            long long r, g, b;
            if (!(in >> r >> g >> b)) {
                throw std::runtime_error("Not enough values");
            }
            if (r < 0 || g < 0 || b < 0 || r > maxc || g > maxc || b > maxc) {
                throw std::runtime_error("Invalid color value");
            }
            Pixel p;
            p.red = scale_to_255(r);
            p.green = scale_to_255(g);
            p.blue = scale_to_255(b);
            img[static_cast<size_t>(x)][static_cast<size_t>(y)] = p;  // column-major
        }
    }

    // ensure no trailing extra pixels
    long long extraToken;
    if (in >> extraToken) {
        throw std::runtime_error("Too many values");
    }
    return img;
    
}

void output_image(const std::string& filename,
                  const Image& image) {
    // TODO(student): implement writing image to file
     if (image.empty() || image[0].empty()) {
        throw std::invalid_argument("Invalid image");
    }
    if (filename.empty()) {
        throw std::invalid_argument("Invalid filename");
    }
    std::ofstream out(filename);
    if (!out) {
        throw std::invalid_argument(std::string("Failed to open ") + filename);
    }

    const size_t W = image.size();
    const size_t H = image[0].size();

    // PPM P3, output row-major order
    out << "P3\n" << W << ' ' << H << "\n255\n";
    for (size_t y = 0; y < H; ++y) {
        for (size_t x = 0; x < W; ++x) {
            const Pixel& p = image[x][y];
            out << static_cast<int>(p.red) << ' '
                << static_cast<int>(p.green) << ' '
                << static_cast<int>(p.blue);
            out << (x + 1 == W ? '\n' : ' ');
        }
    }
}

double map_coordinates(size_t source_dimension,
                       size_t target_dimension,
                       size_t pixel_coordinate) {
    // TODO(student): implement mapping function.
    if (source_dimension == 0 || target_dimension == 0 || target_dimension <= 1) {
        throw std::invalid_argument("Invalid dimension");
    }
    if (pixel_coordinate >= target_dimension) {
        throw std::invalid_argument("Invalid coordinate");
    }
    const double s = static_cast<double>(source_dimension - 1) /
                     static_cast<double>(target_dimension - 1);
    return s * static_cast<double>(pixel_coordinate);
    
}

Pixel bicubic_interpolation(const Image& source_image,
                            double x,
                            double y) {
    // TODO(student): Implement bicubic interpolation
     if (source_image.empty() || source_image[0].empty()) {
        throw std::invalid_argument("Invalid image");
    }
    const int W = static_cast<int>(source_image.size());
    const int H = static_cast<int>(source_image[0].size());
    if (x < 0.0 || y < 0.0 || x > (W - 1) || y > (H - 1)) {
        throw std::invalid_argument("Invalid coordinate");
    }

    int x1 = static_cast<int>(std::floor(x));
    int y1 = static_cast<int>(std::floor(y));
    double tx = x - x1;
    double ty = y - y1;

    Pixel nb[4][4];
    for (int j = -1; j <= 2; ++j) {
        for (int i = -1; i <= 2; ++i) {
            nb[j + 1][i + 1] = get_clamped(source_image, x1 + i, y1 + j);
        }
    }

    double Rx[4], Gx[4], Bx[4];
    for (int r = 0; r < 4; ++r) {
        Rx[r] = cubic(nb[r][0].red, nb[r][1].red, nb[r][2].red, nb[r][3].red, tx);
        Gx[r] = cubic(nb[r][0].green, nb[r][1].green, nb[r][2].green, nb[r][3].green, tx);
        Bx[r] = cubic(nb[r][0].blue, nb[r][1].blue, nb[r][2].blue, nb[r][3].blue, tx);
    }

    double r = cubic(Rx[0], Rx[1], Rx[2], Rx[3], ty);
    double g = cubic(Gx[0], Gx[1], Gx[2], Gx[3], ty);
    double b = cubic(Bx[0], Bx[1], Bx[2], Bx[3], ty);

    Pixel out;
    out.red = round_u8(clampd(r, 0.0, 255.0));
    out.green = round_u8(clampd(g, 0.0, 255.0));
    out.blue = round_u8(clampd(b, 0.0, 255.0));
    return out;
}

Image scale_image(const Image& source_image,
                  size_t target_width,
                  size_t target_height) {
    // TODO(student): add loops to calculate scaled images
 if (source_image.empty() || source_image[0].empty()) {
        throw std::invalid_argument("Invalid image");
    }
    if (target_width == 0 || target_height == 0 ||
        target_width >= MAX_WIDTH || target_height >= MAX_HEIGHT) {
        throw std::invalid_argument("Invalid dimension");
    }

    const size_t srcW = source_image.size();
    const size_t srcH = source_image[0].size();

    Image out(target_width, std::vector<Pixel>(target_height));
    for (size_t tx = 0; tx < target_width; ++tx) {
        double sx = map_coordinates(srcW, target_width, tx);
        for (size_t ty = 0; ty < target_height; ++ty) {
            double sy = map_coordinates(srcH, target_height, ty);
            out[tx][ty] = bicubic_interpolation(source_image, sx, sy);
        }
    }
    return out;

}
