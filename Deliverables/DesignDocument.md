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
![package Diagram](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuIf8JCvEJ4zLK7AD2ix8Br1IgEPIKE2gHOYxXtgYIN51-0fwUdOAS5akXzIy5A210000)




# Low level design

![class diagram](http://www.plantuml.com/plantuml/png/bLNDRjim3BxhAOoSjW4jrZTeaxH0W67DqlIocognYL1PyYIvWn7itMTJZAL9GO7rK9CVF_wG8f5R7xWBVQkPqDpxvlxdqz5s9-R0G_ZqcGa7FC2p1pSg4ZJCoW72dTQJugD6UDz9yjYfmp5CHcqFoiIlxLFKhREo5s4_T33x4KR92Wxbp31AdI7BOSdToageB7uvhC4BfxgWh9ap_kYv2IeCgGJh5J6Kzptim3g9hNLyU9p41viqD3WG8mUrE-VKEzTpiGXwxETL6LZrFjWMdLehrUkG0Eeu0HJX3FXvS2lkvAXmvxWuJihULd6tLfdW7mrnyPcVk8QzuyPpGGroASyJifErdFk3iotXlElqi5PUsDw47G-mjqIwR2GtOTkk3n3B6GTtXhNrKDL3oH5NuWr274AfHtavVE_R5t3dxdOGUcV-ZUSGItM2aIKjfRqSG81wXnNtnoqVMZ0X0nr85QO1asbEMD8HgzcqDqkkkH7mZDiNEunaVsVa6bv68GsIo2FmXz7DJhVMd5W_VZOIFuQtmBhHzjiwoPpLaDZPHPnOC_vr4TZ8N9ik27MPJeRiIi0MtBDH0G5Z0tYKVj4bPRdeb9gOYIklORD6sN7nHgA6Tz2ZFBlXpQ2FTHARAlAcNAFe0m-zJzxdKy2SWfJO7SvbIfdOt-p0THYcTw4gnIYUXu5RY-B8LGS3CdaMsqlUmN42Dh91P7hLQ5AKNLWVOdpS17oBICqkg2oWRBFQrfZVbV16_cyLPQOisBq9RcXE-GCKLSzUjAYF7JW-byVRYJX9aiZn1wTPt2oQgwjcyMM1wrp3-TwoUQyMNw-lYN9JK9ASHy7LOlWVTVACwLYs34LeMggwiCnqEMKrFbO2MI7bKdt4hJU0LSX7eKalgTHtDegwvp9MpQYBgpdLYZ1s2qRshVuB)









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
\<select key scenarios from the requirement document. For each of them define a sequence diagram showing that the scenario can be implemented by the classes and methods in the design>

Order a product Scenario
![Product order Scenario](http://www.plantuml.com/plantuml/png/TP7BIeCn48NtVOhp0WQyInTI11Skj4LTkHkJgGR-9d4oKVwtDo5U2YNRoJTdlaDMMKcqxAR5qtjCznJeZGL7rxXzQGCiSMnmbtDXH74iYpvlnFx24YS63r7zTePljEluCFNKO4FpGUODJHGihnCBgO-XacVrViWiYi1VUEq0uXz1mQ4aHyg_ZqF7SuDrEuX4tWtLBWmUsKRvgG0IyPyqxQLk9BfYzNbENFdB9cY5AJC-2WNrMejjaJhLzzaDhQuC_llQk4iJQyLN75pzbsy0)

Sale Transaction Scenario
![Sale transaction Scenario](http://www.plantuml.com/plantuml/png/ZPB1ZXCn44Jl-Ohw0KQ4NHR80Iq43aWW45akt3fs3x7aQPjsMsZ-7ezcDmJ9h3ZNgsgxLBV5IArESREdSWgiUF8QxxyVJYbZX-s0mwB3bAIGiv1aiweBTw38Tt-K7Pvr-6xmLGCT2R8cNvtXIEgIvuqaOqGURTU2E6FKDE4Abd03KePoPZBsg68Xmawil0WFGML1oVj_Fx8TlnxmnYyHeV5fR2bd_wE-vysNSvpXGt6fYi7I-GTXH5OkB9SPNrRnRixS_3S3th5Jfi9dsyzAOi7cHm-_w1qk3Rli-U85tSfV3lZAv17JJD5cE5AFmXeeGkfqR7XOkt2rM9fOSQA29DoDUpLW7rFX_vt6zcd7q1zTMzqd4lh1o3HFVpVwO6ZOChRg79SorjX1FwuVtPFwHhV5VSkUZ95J42iNu5kA98u_PrQwRtnxZGzIM0t2lt1S0QGpGU9HrxXxSNFBukiKVmC0)

