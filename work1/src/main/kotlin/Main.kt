fun String.isOnlyLetters() = all { it.isLetter() }

fun main(args: Array<String>) {
    var expList = ExpList()
    while(true){
        println("1.Добавить расход")
        println("2.Вывод всех расходов")
        println("3.Подсчет суммы по каждой категории")
        println("4.Поиск по категории")
        println("Выход любая другая клавиша")
        var ch = readlnOrNull().toString()
        if(ch == "1"){
            println("Введите сумму расхода: ")
            val a = readln().toIntOrNull()
            if (a!= null) {
                println("Введите категорию: ")
                val b = readlnOrNull().toString()
                if (b.isOnlyLetters()){
                    println("Введите дату")
                    val c = readlnOrNull().toString()
                    val exp = Expenses(a, b, c)
                    expList.addExpense(exp)
                }
                else{
                    println("Категория должна быть словом")
                }
            }
            else{
                println("Расход должен состоять из чисел")
            }
        }
        else if(ch == "2"){
            expList.printAllExp()
        }
        else if (ch == "3"){
            expList.prinAllSum()
        }
        else if (ch == "4"){
            println("Введите категорию: ")
            val b = readlnOrNull().toString()
            expList.printAllCat(b)
        }
        else{
            break
        }

    }
}