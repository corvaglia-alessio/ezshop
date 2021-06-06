# Design Document 


Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 19 May 2021

Version: 1.1

* Updated low level design after the coding part with respect to version 1.0

Version 1.2

* Design modification for change 2: 
    - Added the map for products in EZShop
    - Added methods for persistance of RFIDs
    - Added new methods in the EZShop class

# Contents

- [High level design](#package-diagram)
- [Low level design](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design must satisfy the Official Requirements document, notably functional and non functional requirements

# High level design 

The followed architectural pattern is the MVC pattern, with a 2-tier structure. 

## Package diagram
![package Diagram](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuIf8JCvEJ4zLK7AD2ix8Br1IgEPIK80BfYIM90A5Qf75KYkIKmjAClFpYj5bSlDJKdF0WhK8Szr3FP6D1afnGVWAUdfs2d1PBeVKl1IWWm00)




# Low level design

![class diagram 2](http://www.plantuml.com/plantuml/png/ZLPDRoCt3BtFhn1oQelT1VQ-23QEDss0RU8cJWlqfaYCBUoCD14vAGP1_tlr8Ploo8UUF7nyZzIG8iTVaQIdQUYxEqSW98gxpzrLtuiT48773m8F3yfP93yfSXw5z23CCFP66VhKTPl0C4XWLO1HU7YTZ0SjZ1Ko15EzH0Jyr87LmZg2hakG-FRtuzwDxrtlTZlGj_O9mNTIqIJxbUnb2DVzBiSljvO-Y8rtEcJVpYDSXi1lOCdvcVrHUoac18_TTuFqvJb7k1_1IpBENeebWgo-zphANVofu4VPmzPBYrBb4CZ08i83qEJj2TaprD2l9oGtn9ogF6Nd8tbZTy7lGHkwbbvVnr85sX4u9OPei8jnnbmWx3mWq4y_Tq4X2M9TWg6XXwEn0mfDZWPcg-3Jg8lYmUps59onDSRcNpTnrAeRMSTOIRRWb9mLLb9No6fUIN_jD3GUNCrhGEND6CjupFZ792qPcla83arX6CG9Kbk3CSevFTuujfB3Wt9U9-lAU_Cc--DH4uZfRLwCXTAmA1gSDYzp0RYu3CG8R9Lu1m2FHOpD38OaaceVZQrhicoSiOJtjd3Jl2vkPGoXzQ5QBdgNFRZnRYYu7CT-NXjKRhBq80cshf3EEujCkM6S2F9nGXaB35OtfocfMtm7pT7B6_7miCCdZCyMKYrfdEny00NcBLGPzniv3s29GHulTkM4JA-mbbrkJbbJJ-dsbSOdCbvPlORdyC35A-Gp_NgukLsVlOt5qJIp5jLrAjY_PSCkryXx9uIeLicjJa1PZ2oBAo09H5PCmOxSycZb0CaO0-KVvxF7kvv3rFkwZZHcE0PZAyvyiZKFUV4R1HlmJzPG0gmZmFJqMcO_yyhS9-DFyEQ5LmaVAHMM3-EY6GxIfAb28qcQi5kUj1GiYBdIPN8vVjdiEGTRTwbuRCS-H7kCevNVZQCkvgMk91dr0-Xk6fx1Sx5o6Nc2Cu1qU4gVncsIVhFavqi1uSS0Sivcbkgi9fsLwoBI9x6qCtwNJFow6Kk7pnySRdgLeShFiOaJfd3FqYkdWMk_FhaSSeXls3QlVdVEk5fy-daQZd64BZNYvWQ3vh4A4tlrcxIw1_zUReTHGkr1_VW-xMrzsNu56tD1Q11ue_BdkXBj81pHQZTaJoszCM6uGSOjOFLVtb2TBV_5oUuWEeKpkORBBMD5wsfLxJ-bLjrGsYXONzusICjeGz3nfXXW_NbteyuJtieAtl3vRoLB6bUhudLQfsgTZR9C2AkMZbOHzplJQvIvQNtalzRYulE5-FXHNFnomJkzzJNBkkDL6VsH5jbPsEfFbr_NZ3U7EBlGse3_btP-LRN4y_FVykhlPSM8tchq6Mr7jVQUtm7TLx1w6lh_0000)





# Verification traceability matrix

\<for each functional requirement from the requirement document, list which classes concur to implement it>

|     | EZShop  |  balanceOperation  |  User | ProductType  | Position  | Order  |  TransactionEntry |  ReturnTransaction |  SaleTransaction | LoyaltyCard  | Customer |
|-----|---|---|---|---|---|---|---|---|---|---| --- |
|FR1  | X  |   | X  |   |   |   |   |   |   |   |   |
|FR3  |  X |   |   | X  | X  |   |   |   |   |   |   |
|FR4  |  X | X  |   | X  |   | X  |   |   |   |   |   |
|FR5  |  X |   |   |   |   |   |   |   |   |  X | X  |
|FR6  |  X |   |   | X  |   |   | X  | X  | X  | X  | X  |
|FR7  |  X | X  |   |   |   |   | X  | X  |   |   |   |
|FR8  |  X | X  |   |   |   |   |   |   |   |   |   |


# Verification sequence diagrams 

# Create new ProductType and assign a position - Scenarios 1.1 1.2
![Create new ProductType and assign a position](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuKfCBialKWZEo2_mJSnBJ4yjuk92uYZesYcuHe445AmK3FKKaejI4qjI0uhoKqgJIq8g2r8rDBaWyY2LIE90vKPw2dcfvGeeYd6QRQodK5gG0Z8xlpYp93C_3u_19deAnQabI0gHf46gOJ9M2gPG9o2KEgJcfG2z3G00)

# Issue, pay and order arrival - Scenarios 3.1 3.2 3.3
![Issue, pay and order arrival](http://www.plantuml.com/plantuml/png/VP7BQiCm44Nt_efPjeiA-duiYbteihZ5pIQxGHeIWIE9qIZ1Vn_5SOWnSNRYtgEh0ogZ84lQj4MbnJCqQnz-qE6Ak1YTuUuJlfU703xWNe6DCL7DXlZcjXYAZFH99XuKEDh10CmEFIe8sCtFF2c898sWqBaV3pmhODAUpHUsw3GjWfcoGrC7OXJhNRxrShIRDbT4Nq_lz6OLizrYUr7vRKRvbxr9MlwxG9bwxwc_X4wiT1aioPdy2Ni0)

# Insert new customer and attach a new Loyalty Card - Scenarios 4.1 4.2
![Insert new customer and attach a loyalty card](http://www.plantuml.com/plantuml/png/XOwxJiH034NxVCKjeM34ycOAj6X5HmSqT5R6Q2AIJ3HsGFltC0fM4XwYzFNnyTsAKjQnQokgDMNiI6B3sQPHD7MVO_MLu-W65i9Zut0hhnWuI-gfHI0bQ896HcstnfTRqtYSk5aqvFJM11R4HjQovT-xvuUO1dYSEWH-QNesmS5XDIs_rJjpwFaTC_QhxjoXpapAEyfXfRfFUsfrNt9uN4osHVY7yDA1LAcEXNbC_xMySX1Myrc-VkznFL6pQx7zsNLpNEWduUBSSX_6hlq0)


# Sale Transaction - Scenarios 6.1 6.2 6.3 6.4 6.6
![Sale Transaction](http://www.plantuml.com/plantuml/png/bL9DZzCm4BttLymH3WIw1RRaW5Oiomb4eDqBjr4yIoqv7ciy1kNVOzSKqcv3nItA-tZFzhk9Yg9fS0libGLkCUuioMBn-mEUlOMxRviT1sXXsS2Ula57Mq4ViyQoV_9qKIc5V8ArSDM0fv_mI0Dd8rODe36Ti4czRhceihrd9N3qe2rib08y20-m0cLO0mg1K21KCf2yMWUw8w4z4AfHpDJYE-bvY1W3jV2oWU-adTYUEf9xR_NeJ9FUTrvbX1PUrLET8bas4_6wWKZw9M5kg9btzlJN3N0ePkW0Gt3ZUnjxJbw_ejAMg-75Rkk9IywSUipallbtRds5-KSkqtZntw7B9P0t9rPptAk_xZqF8IbrRBt63ooFsdta4PsEjoYcI5T7nQfiBYOFGQWd-uCAtE4uaC_t3S___1OoLelr0RnmBtae9RMIFv7ks5oIrAbi53N52NVQythVayMyGuU-f_jWKEc8N17w74ZmuBokgps5ikw6l4c3-mK0)


# Return Transaction - Scenarios 8.1 8.2
![Return Transaction](http://www.plantuml.com/plantuml/png/ZP91Jy9048Nl_8e99qsm2W8g1qCa78reyU9jx8wsITdTJ6TZ-kyTopPIrDH3asRVzsRUJ6PP2RA4NJd0J1p32gky81uCuW-StSBw9SsTXmMC4tWdIR6aBQEjr54uUtBwApj9e2dzH1BOTla8AVv3LUTb0fO-ejJVQ9e0D-Y6dGcPA6QT49Jq9WiOfa8UfYEOZM2ke2SKCX2i52L8Jao0-lczjm9ny4gn89dXGTI396ihN6lhsJxfiTAPCARQrfxKCTUja3m6rFvI_p7HLG9apN_dlsxd_n8tME_8wWxWl7rbCeMia4tKUlT_qsICz3r9xaolPtpHc5912LM7_ZuUlHwcpB6vmn9jHi_Ue507Zig39yQsycI_v6D9VKkoHY_t4m00)
