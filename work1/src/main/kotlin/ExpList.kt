class ExpList {
    private var expList: MutableList<Expenses> = mutableListOf<Expenses>()

    fun addExpense(exp:Expenses){ expList.add(exp) }

    fun printAllExp(){
        for (i in expList){
            i.displayInfo()
        }
    }
    private fun printSumCat(cat:String){
        var sum = 0
        for (i in expList){
            if (i.getCat().equals(cat)){
                sum+=i.getExpSum()
            }
        }
        println("Категория $cat : $sum")
    }
    fun prinAllSum(){
        var catList = mutableSetOf<String>()
        expList.forEach{catList.add(it.getCat())}
        catList.forEach{printSumCat(it)}
    }

    fun printAllCat(cat:String){
        var k = 0
        for (i in expList){
            if (i.getCat() == cat){
                i.displayInfo()
                k = 1
            }
        }
        if (k == 0){
            println("Нет такой категории")
        }
    }
}