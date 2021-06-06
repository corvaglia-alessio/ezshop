# Design Document 


Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 19 May 2021

Version: 1.1

* Updated low level design after the coding part with respect to version 1.0

Version 1.2

* Design modification for change 2: 
    - Added the product class
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

![class diagram 2](http://www.plantuml.com/plantuml/png/ZLPBRnmt3BxFhn1yQeiaGEw1aTYEMmEfxVhH0ht94hqhP4QQY1mN0wF_lNfmb_9g3psj-F7ZOqYHsiz8Ej8oZSDr850QrVN7uSiugWqGGSHt2dS74pnIN0o5Y4f7K6wQHsSSVHY6syHmIE1DWb55-B6u25OvhtHoPaQD2FXX06-L3mJ3K23rzU_xRPXVXp5iDc2l_2D27BIXHOzdUjJ9tV2xdZzTUNgdRcEmAVh3EiDfSlm2da9SHPzjJzM2477uvf0-FLKFDpD4JIxuKtM8e5ZVH9lDG_vfu7izmaFK7hMfBb200mztG4lqRyXHe8v-lY25ASSqVAhAUuhERv8-WdLqhgCzpwLAj3tG4lTrELLtbrSNE4H0e9z-7X9T4-IY9C7227jX0vGwd0Mi4kFBRDdYpcssb9MfByxNtx28rwOLrKum3dR0uPWDng4Rv6mzq_4yMEWqUBPU09hevbp3VSG_5kt9qIef17JCS8WBb9ucOTPhELu6aOeYWWdH5kbBZEv5Z_jK2uZbQvwT1-vMDfg2TSyh0r9S0R87aTZV3i1z4NCdaw29jDbcuI5qKMw3yuGtdhbbM0-kP7PXxQvQ8MiFUt0PmyIudkTnlN1emkBfJXCy12OTLxAP2TEy4DHqKXaP1c-xR3XqZs-WIvs_I2P75B5WabiATKYJOCST670lgSgulTNh19u4bDaILGtOq0NhsNnpU4Szbjl7ZIza_EBj1JobWnIFoKVwbGVtw6raHT_CngYj4sF_ydezt26lWrAgsIDNjW2y5SKihu1YeAh5akJCvQFN4nHXJfH_GgoQ64PnqIxhrjDSONR6Kfxv8biPSjOx0xSG7xsZ0lX0WENqWsU_ydZkY_0dHFSigwHBgIQIAtKa8moZ5OlI2aa_P34HkyIQP90qBJWSVXkN-AfnphFU5jemb6YDT5tABNBkN9RdCembRV5wC65jxScPxq3No_G4KSfR2oypNm6atQcyf0_5zAkdk9ugI3yEK69sqzT6TILNgOigBoYNEpzZBZ-6pjFkjOJTR3GHclZYcoHWSVUaemaMfFONRwwJdl8NzirhlrqYdXtyKv3vsVjHbciapKq2hNFZ9lVgD-tj2F6LRuSpocp1VF-sR7rxFNy5dsD1QX14P-hhtXXj8ANeRPYgfZMzT2FSWSvxmzk_egCsMXbPhEfaz1Qkv1RcMoOMlQgtwlzOzTOTfVU2xUNjdVICtWNjRue3iJ-kxgtBXFTcZ7Ty-HSg9fsgjv85t8RgbPqbJmXRzOhUekmveRUeSClwgF_4rSd74_N-lJhvvMItBtkDl0-zhblyWopHh2-qnAw6lVtRjJXqWoykZgwwpk7_fXrVOZtn-6ReUUtRsp2IjbqAWlP3t6gFRuVXCtYxJED_)





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
