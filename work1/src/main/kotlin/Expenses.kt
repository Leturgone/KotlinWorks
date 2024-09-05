class Expenses(private var exp_sum: Int, private var exp_cat: String, private var exp_date: String) {

    fun displayInfo(){
        println("Сумма расхода: $exp_sum, Категория: $exp_cat, Дата: $exp_date")
    }

    fun getCat():String{ return exp_cat }

    fun getExpSum():Int{ return exp_sum }
}