@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _userId = MutableStateFlow<Int?>(null)

    val monthlyExpenses = _userId.flatMapLatest { userId ->
        userId?.let { repository.getMonthlyExpenses(it) } ?: flowOf(emptyList())
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val monthlyIncome = _userId.flatMapLatest { userId ->
        userId?.let { repository.getMonthlyIncome(it) } ?: flowOf(emptyList())
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setUser(userId: Int) {
        _userId.value = userId
    }

    fun addTransaction(type: TransactionType, amount: Double, category: String) {
        viewModelScope.launch {
            _userId.value?.let { userId ->
                val transaction = Transaction(
                    id = 0,
                    userId = userId,
                    type = type,
                    category = category,
                    amount = amount,
                    date = System.currentTimeMillis().toString()
                )
                repository.addTransaction(transaction)
            }
        }
    }
}