package com.example.bgproject.fragments.question

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val categoryA = ArrayList<Question>()
        val categoryB = ArrayList<Question>()
        val categoryC = ArrayList<Question>()

        val que1 = Question(
            1,
            "Musa can make three payments on his bicycle. The bicycle cost him ₦3000. The first installment will be half of the cost of the bicycle. The second installment will be half of the balance. How much will Musa need to pay on his last installment?",
            listOf(
                "A. ₦750",
                "B. ₦1000",
                "C. ₦150",
                "D. ₦250",
                "E. ₦2500"
            ),
            0
        )
        categoryA.add(que1)

        val que2 = Question(
            2,
            "A mechanic workshop charges a customer ₦850 to repair his bike. The bike parts cost ₦50 and the remainder was for labour. If the cost of labour is ₦100 per hour, how many hours of labour did it take the mechanic workshop to repair the bike?",
            listOf(
                "A. 1",
                "B. 7",
                "C. 8",
                "D. 4",
                "E.10"
            ),
            2
        )
        categoryA.add(que2)

        val que3 = Question(
            3,
            "Suppose you owe ₦40 to each of four friends, ₦10 to each of three friends, and ₦100 to your parents. If two friends each owe you ₦70, what is your net debt?",
            listOf(
                "A. ₦140",
                "B. ₦220",
                "C. ₦150",
                "D. ₦80",
                "E. ₦100"
            ),
            2
        )
        categoryA.add(que3)

        val que4 = Question(
            4,
            "If Adamu issues 50 prepaid cards in 30 minutes, how many cards can he issue in 3 hours?",
            listOf(
                "A. 400",
                "B. 20",
                "C. 600",
                "D. 150",
                "E. 300"
            ),
            4
        )
        categoryA.add(que4)

        val que5 = Question(
            5,
            "Mary, Bilikisu, and Amina need to prepare 500 plates of food. If Mary prepares 150 plates of food; Amina prepares 200 plates of food; how many plates of food should Bilikisu prepare?",
            listOf(
                "A. 300",
                "B. 50",
                "C. 350",
                "D. 150",
                "E. 500"
            ),
            3
        )
        categoryA.add(que5)

        val que6 = Question(
            6,
            "Musa buys 6 sugarcanes for ₦100 each. He sells all 6 for a total of ₦1,000. How much total profit did he make?",
            listOf(
                "A. ₦600",
                "B. ₦900",
                "C. ₦300",
                "D. ₦400",
                "E. ₦6000"
            ),
            4
        )
        categoryA.add(que6)

        val que7 = Question(
            7,
            "What is the next number in the sequence: 1, 2, 3, 5, 8, 13, _?",
            listOf(
                "A. 21",
                "B. 24",
                "C. 34",
                "D. 47",
                "E. 52"
            ),
            0
        )
        categoryB.add(que7)

        val que8 = Question(
            8,
            "What is the sum of 10 and 15",
            listOf(
                "A. 25",
                "B. 35",
                "C. 45",
                "D. 55",
                "E. 65"
            ),
            0
        )
        categoryB.add(que8)

        val que9 = Question(
            9,
            "What is the product of 5 and 7",
            listOf(
                "A. 55",
                "B. 65",
                "C. 35",
                "D. 45",
                "E. 50"
            ),
            2
        )
        categoryB.add(que9)

        val que10 = Question(
            10,
            "What's the difference between 20 and 10",
            listOf(
                "A. 45",
                "B. 23",
                "C. 21",
                "D. 10",
                "E. 5"
            ),
            3
        )
        categoryB.add(que10)

        val que11 = Question(
            11,
            "What is the quotient of 20 divided by 5",
            listOf(
                "A. 9",
                "B. 5",
                "C. 4",
                "D. 3",
                "E. 6"
            ),
            2
        )
        categoryB.add(que11)

        val que12 = Question(
            12,
            "What is the area of a square with a side length of 5cm",
            listOf(
                "A. 75cm",
                "B. 40cm",
                "C. 25cm",
                "D. 34cm",
                "E. 90cm"
            ),
            2
        )
        categoryB.add(que12)

        val que13 = Question(
            13,
            "Which of the following is an example of a traditional farming method?",
            listOf(
                "A. Hydrophonics",
                "B. Aquaphonics",
                "C. Organic Farming",
                "D. Vertical Farming",
                "E. Conventional Farming"
            ),
            4
        )
        categoryC.add(que13)

        val que14 = Question(
            14,
            "What is the primary purpose of using irrigation in agriculture?",
            listOf(
                "A. Pest control",
                "B. Soil aeration",
                "C. Weed suppression",
                "D. Fertilizer application",
                "E. Water supply to crops"
            ),
            4
        )
        categoryC.add(que14)

        val que15 = Question(
            15,
            "Which of the following is a common cereal crop",
            listOf(
                "A. Tomato",
                "B. Potato",
                "C. Wheat",
                "D. Carrot",
                "E. Lettuce"
            ),
            2
        )
        categoryC.add(que15)

        val que16 = Question(
            16,
            "What is the process of removing weeds from a cultivates field called ?",
            listOf(
                "A. Pollination",
                "B. Fertilzation",
                "C. Germination",
                "D. Irrigation",
                "E. Weed control"
            ),
            4
        )
        categoryC.add(que16)

        val que17 = Question(
            17,
            "Which farming practice involves the cultivation of fruits and vegetables without the use of synthetic fertilizers and pesticides?",
            listOf(
                "A. Organic Farming",
                "B. Intensive Farming",
                "C. Subsistence farming",
                "D. Commercial Farming",
                "E. Precision Farming"
            ),
            0
        )
        categoryC.add(que17)


        val que18 = Question(
            18,
            "What is the purpose of crop rotation in farming?",
            listOf(
                "A. Maximize water usage",
                "B. Improve soil fertility",
                "C. increase crop diversity",
                "D. control pest infestations",
                "E. accelerate crop growth"
            ),
            1
        )
        categoryC.add(que18)

        for (i in 0 until 5) {
            val figureA = categoryA.random()
            val figureB = categoryB.random()
            val figureC = categoryC.random()

            questionList.add(figureA)
            questionList.add(figureB)
            questionList.add(figureC)

            categoryA.remove(figureA)
            categoryB.remove(figureB)
            categoryC.remove(figureC)

        }


        return questionList
    }
}