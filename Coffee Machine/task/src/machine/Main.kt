package machine
import java.util.Scanner

class CoffeeMachine {

    // Machine Supplies
    var water: Int = 0
    var milk: Int = 0
    var coffeeBeans: Int = 0
    var money: Int = 0
    var cups: Int = 0

    // Constants used to give name to beverages properties. This prevents typos.
    val ingWater = "water"
    val ingMilk = "milk"
    val ingCoffeeBeans = "coffeeBeans"
    val supMoney = "price"

    // Beverage Recipes/Ingredients
    private val espresso = HashMap<String, Int>()
    private val cappuccino = HashMap<String, Int>()
    private val latte = HashMap<String, Int>()

    // Scanner - for dealing with input/output from users
    val scanner = Scanner(System.`in`)

    fun bootUpCoffeeMachine(){
        loadBeverageRecipes()
        loadSupplies()

        // printSuppliesStatus()
        // there is still work ToDo
     }
    fun loadBeverageRecipes(){
        loadBeverageEspresso()
        loadBeverageLatte()
        loadBeverageCappuccino()
    }
    fun loadSupplies(){
        loadIngredients()
        loadCups()
        loadMoney()
    }
    private fun loadIngredients() {
        water = 400
        milk = 540
        coffeeBeans = 120
    }
    private fun loadBeverageEspresso(){
        espresso.put(ingWater, 250)
        espresso.put(ingMilk, 0)
        espresso.put(ingCoffeeBeans, 16)
        espresso.put(supMoney, 4)
    }
    private fun loadBeverageLatte(){
        latte.put(ingWater, 350)
        latte.put(ingMilk, 75)
        latte.put(ingCoffeeBeans, 20)
        latte.put(supMoney, 7)
    }
    private fun loadBeverageCappuccino(){
        cappuccino.put(ingWater, 200)
        cappuccino.put(ingMilk, 100)
        cappuccino.put(ingCoffeeBeans, 12)
        cappuccino.put(supMoney, 6)
    }
    private fun loadMoney(){
        money = 550
    }
    private fun loadCups(){
        cups = 9
    }
    fun buy(){
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val option = scanner.next()

        val choosenBeverage = when (option){
            "1" -> {espresso}
            "2" -> {latte}
            "3" -> {cappuccino}
            "back" -> return
            else -> {HashMap<String, Int>()}
        }
       if (hasSupplies(choosenBeverage)){
           brewBeverage(choosenBeverage)
           println("I have enough resources, making you a coffee!")
       }
    }
    private fun brewBeverage(beverage: HashMap<String, Int>) {
        money += beverage[supMoney]!!
        milk -= beverage[ingMilk]!!
        coffeeBeans -= beverage[ingCoffeeBeans]!!
        water -= beverage[ingWater]!!
        cups--
    }
    private fun hasSupplies(beverage: HashMap<String, Int>): Boolean {
        // return OK unless something different is discovered below
        var returnValue = true
        var missingSupply: String = ""

        // check supplies
        when{
            beverage[ingWater]!! > water -> {
                missingSupply = ingWater
                returnValue = false
            }
            beverage[ingMilk]!! > milk -> {
                missingSupply = ingMilk
                returnValue = false
            }
            beverage[ingCoffeeBeans]!! > coffeeBeans -> {
                missingSupply = ingCoffeeBeans
                returnValue = false
            }
            cups <= 0 -> {
                missingSupply = "cups"
                returnValue = false
            }
            else -> {}
        }
        // print only if something is missing
        if (missingSupply != "") {
            println("Sorry, not enough $missingSupply!")
        }
        return returnValue
    }
    private fun fill(){
        println("Write how many ml of water do you want to add: ")
        water += scanner.nextInt()
        println("Write how many ml of milk do you want to add: ")
        milk += scanner.nextInt()
        println("Write how many grams of coffee beans do you want to add: ")
        coffeeBeans += scanner.nextInt()
        println("Write how many disposable cups of coffee do you want to add: ")
        cups += scanner.nextInt()
    }
    private fun take(){
        println("I gave you $money")
        money = 0
    }
    private fun printSuppliesStatus() {
        println()
        println("The coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$coffeeBeans of coffee beans")
        println("$cups of disposable cups")
        println("$$money of money")
        // println()
    }
    fun queryAction() {
        var action: String = ""
        while (action != "exit") {
            print("Write action (buy, fill, take, remaining, exit): ")
            action = scanner.next()
            processAction(action)
        }
    }
    fun processAction(action: String){
        when(action){
            "buy" -> buy()
            "take" -> take()
            "fill" -> fill()
            "remaining" -> printSuppliesStatus()
            "exit" -> return
            else -> println("Unrecognized Action. Please try again.")
        }
        println()
        // printSuppliesStatus()
    }
}

fun main() {

    // aux var to interact with default I/O
    val scanner = Scanner(System.`in`)

    // instantiate the CoffeeMachine, represents bringind a new machine to the world!
    val coffeeMachine = CoffeeMachine()

    // turn on Machine (initialize variables, parameters and stuff)
    coffeeMachine.bootUpCoffeeMachine()

    // request what to do
    coffeeMachine.queryAction()
}