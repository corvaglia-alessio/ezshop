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

# Order a product Scenario
![Product order Scenario](http://www.plantuml.com/plantuml/png/TP7BIeCn48NtVOhp0WQyInTI11Skj4LTkHkJgGR-9d4oKVwtDo5U2YNRoJTdlaDMMKcqxAR5qtjCznJeZGL7rxXzQGCiSMnmbtDXH74iYpvlnFx24YS63r7zTePljEluCFNKO4FpGUODJHGihnCBgO-XacVrViWiYi1VUEq0uXz1mQ4aHyg_ZqF7SuDrEuX4tWtLBWmUsKRvgG0IyPyqxQLk9BfYzNbENFdB9cY5AJC-2WNrMejjaJhLzzaDhQuC_llQk4iJQyLN75pzbsy0)

# Sale Transaction Scenario
![Sale transaction Scenario](http://www.plantuml.com/plantuml/png/ZPB1ZXCn44Jl-Ohw0KQ4NHR80Iq43aWW45akt3fs3x7aQPjsMsZ-7ezcDmJ9h3ZNgsgxLBV5IArESREdSWgiUF8QxxyVJYbZX-s0mwB3bAIGiv1aiweBTw38Tt-K7Pvr-6xmLGCT2R8cNvtXIEgIvuqaOqGURTU2E6FKDE4Abd03KePoPZBsg68Xmawil0WFGML1oVj_Fx8TlnxmnYyHeV5fR2bd_wE-vysNSvpXGt6fYi7I-GTXH5OkB9SPNrRnRixS_3S3th5Jfi9dsyzAOi7cHm-_w1qk3Rli-U85tSfV3lZAv17JJD5cE5AFmXeeGkfqR7XOkt2rM9fOSQA29DoDUpLW7rFX_vt6zcd7q1zTMzqd4lh1o3HFVpVwO6ZOChRg79SorjX1FwuVtPFwHhV5VSkUZ95J42iNu5kA98u_PrQwRtnxZGzIM0t2lt1S0QGpGU9HrxXxSNFBukiKVmC0)

# Manage payment with credit card Scenario
![Manage payment with credit card Scenario](http://www.plantuml.com/plantuml/png/PS-xIWOn40NWFaznNi205vijP2LOM2a85dPZCii6SmapIU7RkyeAWR-Qudo7cLcra_IHat6aUsJ1rGEUFjxssh3WskAvA4k75mxn62J1dF4CRopUASL0dMUACl8dozHmVw4xCXV5ZyCjkBLur2_q-bzLIemcqREvzFFQEulNNuP6SRxh_6N1lSLBOw4UQu4Exrbr6obzex5iLJ87it898wSV)

# Insert new customer and give a loyalty card Scenario
![Insert new customer and give a loyalty card Scenario](http://www.plantuml.com/plantuml/png/XPBFJiCm38VlUmg_01NXFnpGgehR10TktAmqKoFIkeeTfhuz5fbeYhPoYwpl-zcnl6N1AAaFcmQvyox2nJCqYOLwVJ-1gQ3cJnXTP1emG8k280JIER17R7FaivjZLBsgvg0nqfTl7KFdUHbMzdtvUEzeLFLQrUXG70pkCASSl7HPocGvQsqjfK5Klbd8cEkRlrpPxhQ2rna5-sEtSvFYKtSdqPEVk5_C4MZ281DOZ6NoBfSRhQhoS5ufmRBBOnweP5UdCfSLrCnalI9BJUXtMslxDMOzuVzz6psDNUbXZZktTAFtyAPNW-9f04xMEkPz2c42hUufzYfktT3g7Ny3)
