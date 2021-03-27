package vanthan99.harmic.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vanthan99.harmic.dto.CategoryDTO;
import vanthan99.harmic.dto.DiscountDTO;
import vanthan99.harmic.dto.DiscountDetailDTO;
import vanthan99.harmic.dto.ProductDTO;
import vanthan99.harmic.entities.*;
import vanthan99.harmic.entities.enums.ERole;
import vanthan99.harmic.repositories.*;
import vanthan99.harmic.services.CategoryService;
import vanthan99.harmic.services.DiscountDetailService;
import vanthan99.harmic.services.DiscountService;
import vanthan99.harmic.services.ProductService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class InitData {

    @Autowired
    CategoryRepository catRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    BannerRepository bannerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountService discountService;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    DiscountDetailService discountDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @PostConstruct
    public void init() {
        initCategory();
        initBanner();
        initProduct();
        initDiscount();
        initDiscountDetail();
        initUser();
        initReview();
    }

    private void initReview() {
        List<Product> products = productRepository.findAll();
        List<User> users = userRepository.findAll();
        Review review = new Review();
        products.forEach(product -> {
            review.setProduct(product);
            review.setEnable(true);
            review.setContent("Sed ligula sapien, fermentum id est eget, viverra auctor sem. Vivamus maximus enim vitae urna porta, ut euismod nibh lacinia ellentesque at diam voluptas quas nisi, culpa in accusantium");
            for (int i = 0; i <= getRandomInt(users.size(), 2); i++) {
                review.setId(null);
                review.setRate((long) getRandomInt(5, 1));
                review.setUser(users.get(getRandomInt(users.size()-1,0)));
                reviewRepository.save(review);
            }
        });
    }

    private void initUser() {
        User user = new User();

        user.setEmail("vanthan.ad.it@gmail.com");
        user.setPassword(passwordEncoder.encode("than"));
        user.setFullName("Trương Văn Thân");
        user.setPhoneNumber("0927482422");
        user.setRole(ERole.ADMIN);
        user.setEnable(true);
        userRepository.save(user);

        user.setEmail("nhungnguyen@gmail.com");
        user.setPassword(passwordEncoder.encode("than"));
        user.setFullName("Nguyễn Thị Nhung");
        user.setPhoneNumber("038247238");
        user.setRole(ERole.MEMBER);
        userRepository.save(user);

        user.setEmail("damvinhung@gmail.com");
        user.setPassword(passwordEncoder.encode("than"));
        user.setFullName("Đàm Vĩnh Hưng");
        user.setPhoneNumber("0927482425");
        user.setRole(ERole.MEMBER);
        userRepository.save(user);

        user.setEmail("mytam@gmail.com");
        user.setPassword(passwordEncoder.encode("than"));
        user.setFullName("Ca Sĩ Mỹ Tâm");
        user.setPhoneNumber("0927482421");
        user.setRole(ERole.MEMBER);
        userRepository.save(user);

        user.setEmail("duymanh@gmail.com");
        user.setPassword(passwordEncoder.encode("than"));
        user.setFullName("Đỗ Duy Mạnh");
        user.setPhoneNumber("0927482420");
        user.setRole(ERole.MEMBER);
        userRepository.save(user);

        user.setEmail("quanghai@gmail.com");
        user.setPassword(passwordEncoder.encode("than"));
        user.setFullName("Nguyễn Quang Hải");
        user.setPhoneNumber("0927482428");
        user.setRole(ERole.MEMBER);
        userRepository.save(user);
    }

    private void initDiscountDetail() {
        List<Discount> discounts = discountRepository.findAll();

        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            int randomNumber = getRandomInt(discounts.size(), 0);
            DiscountDetailDTO discountDetailDTO = new DiscountDetailDTO();
            if (randomNumber != discounts.size()) {
                discountDetailDTO.setDiscountId(discounts.get(randomNumber).getId());
                discountDetailDTO.setProductId(product.getId());
                discountDetailDTO.setRate(getRandomDouble(0.4, 0.05));
                discountDetailService.save(discountDetailDTO, true);
            }
        });

    }

    private int getRandomInt(int max, int min) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private double getRandomDouble(double max, double min) {
        double random = (Math.random() * ((max - min)));
        return (double) Math.round(random * 100) / 100;
    }

    private void initDiscount() {
        DiscountDTO dto1 = new DiscountDTO();
        dto1.setName("Sale tẹt ga bye tháng 3");
        dto1.setNote("Khuyến mãi tháng 3");
        dto1.setStartDay(LocalDate.of(2021, 3, 27));
        dto1.setEndDay(LocalDate.of(2021, 3, 30));

        DiscountDTO dto2 = new DiscountDTO();
        dto2.setName("chào tháng 4");
        dto2.setNote("Khuyến mãi tháng 4");
        dto2.setStartDay(LocalDate.of(2021, 4, 1));
        dto2.setEndDay(LocalDate.of(2021, 4, 10));

        DiscountDTO dto3 = new DiscountDTO();
        dto3.setName("Chào tháng 5");
        dto3.setNote("Khuyến mãi tháng 5");
        dto3.setStartDay(LocalDate.of(2021, 5, 1));
        dto3.setEndDay(LocalDate.of(2021, 5, 10));

        DiscountDTO dto4 = new DiscountDTO();
        dto4.setName("Chào tháng 6");
        dto4.setNote("Khuyến mãi tháng 6");
        dto4.setStartDay(LocalDate.of(2021, 6, 1));
        dto4.setEndDay(LocalDate.of(2021, 6, 10));

        DiscountDTO dto5 = new DiscountDTO();
        dto5.setName("Chào tháng 7");
        dto5.setNote("Khuyến mãi tháng 7");
        dto5.setStartDay(LocalDate.of(2021, 7, 1));
        dto5.setEndDay(LocalDate.of(2021, 7, 10));
        discountService.save(dto1);
        discountService.save(dto2);
        discountService.save(dto3);
        discountService.save(dto4);
        discountService.save(dto5);
    }

    static final List<String> CATEGORIES = Arrays.asList(
            "Fresh Fruits",
            "Vegetable",
            "Fish & Meat",
            "Wheat"
    );


    private void initCategory() {
        CATEGORIES.forEach(s -> categoryService.save(new CategoryDTO(null, s)));
    }


    private void initProduct() {
        List<ProductDTO> PRODUCTS = Arrays.asList(
                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Black Pepper Grains",
                        "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-1-270x300.jpg",
                        100L,
                        80L
                ),
                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Peanut Big Bean", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-2-270x300.jpg",
                        100L,
                        80L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Dried Lemon Green", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-3-270x300.jpg",
                        80L,
                        100L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Natural Coconut", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-4-270x300.jpg",
                        150L,
                        110L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Black Peppepr Read", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-5-270x300.jpg",
                        200L,
                        50L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Green Vegetable", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-6-270x300.jpg",
                        290L,
                        170L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Lemon Juice", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-7-270x300.jpg",
                        100L,
                        30L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Cow Milk & Meat", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-8-270x300.jpg",
                        50L,
                        10L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Black Pepper Grains", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-9-270x300.jpg",
                        190L,
                        60L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Peanut Big Bean", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-10-270x300.jpg",
                        10L,
                        60L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Dried Lemon Green", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-11-270x300.jpg",
                        100L,
                        50L
                ),

                new ProductDTO(
                        null,
                        getCategory().getId(),
                        "Natural Coconut", "Lorem ipsum dolor sit amet, consecte adipisicin elit, sed do eiusmod tempor incidi ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercita ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore.",
                        "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/product/medium-size/1-12-270x300.jpg",
                        70L,
                        50L
                )
        );
        PRODUCTS.forEach(dto -> productService.save(dto));
    }

    private Category getCategory() {
        int randomNumber = new Random().nextInt((CATEGORIES.size() - 1) + 1);
        String nameRandom = CATEGORIES.get(randomNumber);
//        System.out.println("nameRandom = " + nameRandom);
        return catRepo.findByName(CATEGORIES.get(randomNumber));
//        return catRepo.findByName("Wheat");
    }

    static final List<Banner> BANNERS = Arrays.asList(
            new Banner(
                    "Natural & Organic",
                    "-40% Offer All \n  Products.",
                    "https://www.facebook.com/vanthan.ad.it/",
                    "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/slider/inner-img/1-3-601x534.png"
            ),
            new Banner(
                    "Natural & Organic",
                    "-40% Offer All \n  Products.",
                    "https://www.facebook.com/vanthan.ad.it/",
                    "https://htmldemo.hasthemes.com/harmic/harmic/assets/images/slider/inner-img/2-1-601x426.png"
            )

    );

    private void initBanner() {
        BANNERS.forEach(banner -> bannerRepository.save(banner));
    }

}
