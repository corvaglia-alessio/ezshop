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

![class diagram](http://www.plantuml.com/plantuml/png/VLNHJYmt37tFLqJxr5QzgFp1PPUBX5J15fQNlfd4x4RD9DF4izK8zT-RoNWpwMG53s2V7FlO7bjyZmI1XiwA9qye8SgdMt5dhJmY4ORugumNGtaNAGoAV8WId9OQ2CGC3G6Zf1EGX83ITBqrYkmeDGPpHYq_Wky4nEYL0JBUoHESKRuZEkaxGuJwHc1AwJoX4Cecglpnv-l9zv_2-kCHzQDxYnW492smMx3W58gaYlJJpq851CA9a1oD5ekJsaZTJ4wSFSQ7NdF4YpcUQ7fCEiRDV_smPDq7hrE7Xx57ZYiOYwquh5bXB5qXst4BOUSrDYznEzvZLC7qqvoAuXy3E38qBYNuQ9XXOXpmEUZSM0zZDXzyxIKpeF91P-ykfAy2jfIQmPYx-J0ETqCat-MWpclpCIu0zrk0d67nEDy5Y6M8EmWwEK04wZGv1z-exBrn59yTS_DYleB5Gm0NGN6peFLbMdvwNN-3X-0xng7lxNXlelA3enSWF7WcNN_aCEMxVY2SoqbZP1YTRgfXwHO_ObCwT_I2D0Jt_uUGCQvjbcfeHUnw0eNcZ3k8fpsC7Jggm83Qq3HKVYg2cRx6MZPl3j_SMzu-_l2P7E-SliVtP2p3Ot82_hsSytHhybF8z3Cayr4Nqq67sUaJxnyVvfVWBIxHrONCEVePu3JiJHULVRsS4ByRXNiCRyvG1fmdZDdwco-hqkBJuch1H8ki4q3znQR5Czfabx1yHJd6JrQchNIBbfIHW8QuH5_E8MigTcQfCf-5_RiVmT8uVIUkKYNpCeoykTbCN7DqM5MvMakEfW0CF6gP4Gs4Ss8VQKwR5o9_WfbH7SzAj-ckMTfPDbGmgrVYXT38NvEeilnm54RvMGzyLMw9uaLzxZ50gGkwYJXPcidp_oAvkTt8Rz_avfTDNkSMhlTKb3tQ_7PpmvJR5MMnwonvbKJ-dNsEdEL4zV5NgNc9siAgf-kIpVZ46gWBMItHLzpsqui6-Ier-sigzrsDekcvpTKosk8QJhCYuZiwFNJsFm00)









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
![Sale Transaction](http://www.plantuml.com/plantuml/png/bL8zZzH03EtpArn2GM1lEMv9WKuSHmKYaBs6pifuoKYJySXnGFdtp2IxaFtMTL7UntiU-xvJ5ErRbs6jBF20NMD9icpx0Qy-mEFFgk401Ino6EaLEbeB-YvgBFiNBxE9aiW7M04tENZw0qSQE1cnp06DAOLDN-kQaohQUrO2Hnije58Ai15kOGdAi08K0g50g6IWzseTQ4D28n0ceovHe--ivovY25J0snn-aPPYQof9dhpLlPbclH-zoW05t8vJ7G99TJhXNNmwqkyzne8QUIU7lyk1Gp931nY26pxPhkOuqmzKMlDaUBNQQkOIC-SUPtBVNyvDIxYqutGPRvuTkbW0UNDWTOvxyz-zvZRqIYLRhzrdbgDsNtX0fyC3YadIvLwnINOk9jw1K4tsDoMunA4b7zyRNl_xBMIi9kiTUELTRiUzRybVIHisroJJgLIAsdTpxgpdtTYJnNn4Xxwcfs1GQO-yHRu54jmvhyQZFOAYxfwywLlt5m00)


# Return Transaction - Scenarios 8.1 8.2
![Return Transaction](http://www.plantuml.com/plantuml/png/ZP9FJy904CNlV8e99qsm2W8g1qCa78reyU9jx3vjOzdTRASn_VQEPOdKFy53asRVxytCcyoy4WvIRyeUP-82BRZA2uHUB_xGoJKjdzBSUPhHCA5NICebre5jfOx2sQFZNzbHGctf1qWTR9UFaE8_L7MU9sJn7gN3ZSO9XHPT1MVgJ1IpJa0bNcH6_LJWQJoWoO2c2dgmm51jfIX9SWGGw-Utteh4qJDYGPZ-tgZh8dk3BAs4HhjEjaFkFNPonLdMZOV2Kzq5vBvcxIhD7pak4e8r_qrzkKlzAQwusS1gSZhTlGQOGXOSJDGERlsgdJ7Ijv3ScOEUuLbhIeMbhZhqrydepGHaBfWRBjbcUFI61HquAdSUWNULHzlzVfVKDuSrUgyV)
