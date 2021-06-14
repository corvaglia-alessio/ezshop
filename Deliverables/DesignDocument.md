# Design Document 


Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 6 June 2021

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

![class diagram](http://www.plantuml.com/plantuml/png/ZLPHRnit37w_No7aQHlQ0dqlWZPninde4iznDc1lYaJRGk-agyJBS0ZstoTAigcS_B2d4pz-92LIvEbpGEbn6lhktY486SJzn-vBtui3888FxqGuBPIp0VsaqFaWf0TXXh4tok27hjj4XWa8LaKu20y_9kD12sE5ZCvKBqE0yA43guLr25sN8F7rxyUZ6r-xtXqEeDVsAO3lfC99zZUobz5TzxiSFwqjlXCRxtIClfj7k8wENy2YypFhoVPQJ05ywBwPW9-UiuU74Rn4u-orM2A1hH-y9dD7dmf-b3tilBH1gkmYCB3mi0MSl7r3zWmrzDife1iefYghh7n4R-mXwZreWxVIwrjALQITWPfupikrsDwjLw7p401_-hcBT8b0IOc2XXxEmW4mLfY4aAM2JwCk5bjpE28fOrsCpLytiTUg5DcEiH9imIanAwo4hf2R-KRwMwUXqOIRUGL1UJDI3iyH_vYaHOCpRy45KnWcX0bIJQCmoZajxnnBIU51EQ-JzCLxyoBxyrOJ6D9fziP2gHOP3KwR_Lm0JYu3v86buky4X7CIgP9Ha8XI7KdOkIRAnXcBuS4MRchMnPKa5rgVikL8kwp1dNT3mUKuzlFA1EKcYrk9i7E5T5bPp9GRnWaXRoUciS1WTRER4hh53z1ilPo8EuSLFc6yjnXgIUDWbnqeC2yno-6uaVC05Xdatc9L3aZq1clPvUQK6VMKRbyfV2A7BrQlu3akE7c5VA5UrBWNRsDHjCKiHiiy5UpVCbwNC-Ir4q9KSsIj4r2c8flH24W68bjCKIvSMbevG1B6IFd7-QpnhcSNzR2kFOqP9cSiKSydMNEJ5xrHi07_P0qcm3g4a5O_IkzdNkdx9Fm9tknvb3HRoXkXJ5tOqG5mRH4Eu4igyp5ZQx9C99BstWqF-pq4hAEayl6H7OdHUNEOwW019KwXM-QZb6MjsNcP3tLnL84e1altgKGiKxL97gc1SmRPww8hwtgXKTy1xwVX6JoN91UBvqG60fvMwU-xIwPVBVhvMa3y60WSi-dOEggXlN9UQ2bxA2MYNv-X7wYnUFh3mgcVgWXLVFQD732vUvPUEGsS-zMRAoW7Ec5RlFhi7F5cyRhWdZlx4JhdY8jB0ivZvOPgzPkqkWV_Mcw7KK8TGNt_DXrjVTb-1Kkn81O8l57vHS16wQP6Jsx8cjhqplIm1Kcpnkg_lC4wMdv5PNKqUWjdSWsNMyOMhQgrQXzZZNL3QRqqxx7MoOBHki3pJJ6mi6_KZNNky6BMuWsVNwviqgXQAnxQRQXQsLYM3c6hfQAreE56z1PbRXeV-VKkhZvUYVVlnTKlLwSPsshudzBgcfz5nqEKz4GZTZPixT-EnQvfV7PnSTIr3lylxV8GQucN9qFBM_wl2uEqrL1Wj6tYMdjvEdIVmUffwFy3)





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
