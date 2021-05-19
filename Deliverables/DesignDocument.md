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

![class diagram 2](http://www.plantuml.com/plantuml/png/ZLRVRnit37w_lqB8qpQq1VfU18sJPWlG9PxZRC3U58cnXTv9Lv6NuH3iVvz-oAPyCh0--VYHtqU94Yb_HfAUfg7lxXs1a2ZkFtPNVIzsG0GUtmayV2XdaVoao7aKqeCmmzWRPUX3rwr3X442gmACmiFtoNZGmbWXWvZg9IBWXmwi5jOHT5s2n9U_7_TkVEjwjzk1lhDF2BwJYYRPhsGlWrptknm_tLbw9zRUwP1zEuzm6OHVmPBpC_iZzr9C21wxhmRfqtDMU1Z1IpBENeebWin-y3hINVofu4VPmzPBYr9b2MHWeR01chmz2VOCDU7N4v8ROavLlhBpaRonk-3te0rTIw-lOwb2s170pWC2_VHp5p1947SU30qz78qTK3Z6Q62s2ZwDkZ0sPhUdw0p5DpR_kebLgtfd7cCbsIAcvAomahf2LlDA-ckdeV7WQhu1LDwCiL37Z7zCqfAXcPVWq9G8WpX1EhXWZ79EdxUEhUJme9pNoRhothpA_hZK16BQpOknK8uaaWQdpSjS02uk0r61hQ9t0F1On7XSmP14KksZiNLDbhKpbl31bjZKaOjx5oMqFbJBHU_o36wz6meknx6VRmmgDrdQI8AjAq7ddOMct31E17aveOm51gkRrPJKBRu3Qi6q8suFTlY4yTf2gcKO9pileC2yXYhZVYtd0Imno0t4hfom2cUiZIutfmoYftJxoi6dOBoo-WQUmmSNhmG7uDyouvQD_DO98QfcljC9AAE9QR554q5anXJi65i-hHmW6MC8-SVvxF6kPubwOjPAOuQBMB5YJoPRSnCMlr6m1lza3IN0EW9CNzzBR-QuqfV9-1EyUU5MvoMbZkF5EA_JhfeqbJIIf0cxvKfBmUuqYvVEglJBRCqvsBfFnMCxdaEqntJrqmvOTN6VwqgIKT-0xgVX6JmNAvUHEom3I8Ulz3Xj4_MB9JzV2WW_1f1pDdUzpchIMhak8ZrAvJZZ4sBY-s8i7HuWE4oEAaELdxM94oQvP-cLqy2rlpcv77A8EsmFhzuxPrmj7b_kXgEEqAL6VBZ1e7ciPE9P_IQjxi6_bTjXb51xKD--JdjRNxPVmSPS40u8l57vPQr8Ew1DK793zjJKMzF31cHyCwp-onkgiwK6nEmEf5Cu1zTmkMNCQ5qjg_slqhARa5O5wylRYhGHhGGTRug1Xhzmj-pKuONMuaqy_x5ZIkDgMJnEsrIris6M3cbPnS6CF1Bo7rPnyV52l7ylBdwvu3dT-fe1tF7u2_uObg8piUMV3hIkQLcME3ka6i4V3UFnyt-1RK-VZ-Cti8u8thgT6MtRh_QUx-lkCrWz3Vr_)





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
