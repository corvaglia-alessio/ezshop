# Design Document 


Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 23 April 2021

Version: 1.0


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

![class diagram](http://www.plantuml.com/plantuml/png/XLPDRnit4BtpLn0wjKLYrFV0Y2KdW80qLXtxqXj3JcIYN7B3cNMnCFhVkoH78ijLqukNy_ZcpOTcTlqUIKMQ1YS-1mAfK7w-5hVEoICGGSGt4ay77JnId3I5Y595a7OOdTMMheGuB0oB15ulCCe8toSRmKZhfLh4j5E8W5S2l94-40YH8VdXZo_FONmLBXoFOFR-2I4AfMbIRgkSMkJ4Rsfyj_VqHXvYC4lqntc46paM0-jrShsH4q94ySaYlVjQ1Ev7Y8fiy3UoHnhl-sYIUqYF1lwY73n6vL7f8e4Lw1GUWARe_qME5LhHTnDI659CpISK4H3efv-5Zg08Ka6BOS31sJW2tJgN32mMuzDesEF17fzfkKmjjRuymrHLcouMlufni8x3CHkCGpV8Tjwgk0i6LZUudUy0TRHZglySyVT9UR8qrnG2McPOn0doxx4OevhpyMEeLhw8e4CqsRgDqRueTqurWvYh-MOzSAUJqn2C_JPNWEkjG5Ae5kkT03mtSQUYMGn5fFHpCXx3AieXM4zuxvcRzwmRfoHXpAbR8Ttsly7760R6rJYw-SwY3fEd1qNm69Xq-PBTT1Z6YQ2aixIHOV1cbGs7xl7SXnxaYhgfpnSnOvnR2LLfVR3B0Xhi2-mKFX_KF82d1enWBAMcDbTP8zDxRCtcoU7NorEUlb9Y8UEjDtVmTJdKvZ5v0VxcDrT-tSXN8QNCxvozEH-z6Y0RuyBxAyHo4uE3wjriID4O2y0oRAMDohQfAyJtLiC1ufEtb04V232VllDc5HvlLHEO4KvTMNJ0GQFIV_BHmGku9XTEjSha5SiQ8HgklakgIGH9qOJYijRvJPWpqcoKuATMkZ0hH_DESJEhywTwmoNfvNXAFSzyGLctUdO3pTvOK0RtHcOqpKYH2KX5EezJgIGpcjNhOjVofSmVfYRg1qznlf6mF2naIbShk_pdnmLhGIjEgIZsyDxKcYgXhwhFlFlm_NyrJMOzHRwsyz4tx_AKRBjlTovE3SbDS3M9NFwXa9lhZNpxLcv-sUH5NSFj1ehpKcn-lRfYodL7gUTM9U-1o7-ppPubd6Y6ivFccLydrbnT3hbgfkY1Df5ko7_8NSsFM24_acQxftBTJIlMDQ-qrenrScjEFoBYFNWp3UuV)







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
