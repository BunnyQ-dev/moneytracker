object ExpenseCategories {
    const val FOOD = "Food"
    const val CLOTHING = "Clothing"
    const val TRANSPORTATION = "Transportation"
    const val ENTERTAINMENT = "Entertainment"
    const val HEALTHCARE = "Healthcare"
    const val UTILITIES = "Utilities"
    const val HOUSING = "Housing"
    const val EDUCATION = "Education"
    const val OTHER = "Other"

    val DEFAULT_CATEGORIES = listOf(
        FOOD,
        CLOTHING,
        TRANSPORTATION,
        ENTERTAINMENT,
        HEALTHCARE,
        UTILITIES,
        HOUSING,
        EDUCATION,
        OTHER
    )
}

object IncomeCategories {
    const val WORK = "Work"
    const val BUSINESS = "Business"
    const val INVESTMENTS = "Investments"
    const val FREELANCE = "Freelance"
    const val OTHER = "Other"

    val DEFAULT_CATEGORIES = listOf(
        WORK,
        BUSINESS,
        INVESTMENTS,
        FREELANCE,
        OTHER
    )
}