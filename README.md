# Princípio Aberto/Fechado (OCP) + Android + Kotlin + MVVM 


O Princípio Aberto/Fechado (OCP) é um dos princípios SOLID de programação orientada a objetos que visa tornar o código mais flexível e fácil de manter. Ele afirma que uma classe deve estar aberta para extensão, mas fechada para modificação. Em outras palavras, você deve ser capaz de adicionar novos recursos sem modificar o código existente.

Para demonstrar como aplicar o OCP em uma arquitetura MVVM em Kotlin Android, vamos imaginar um exemplo em que um chefe de cozinha pode preparar diferentes tipos de pratos (saladas, sopas, massas, etc.).

Primeiramente, vamos criar uma interface Prato que define um método preparar que será implementado pelas diferentes classes de pratos:

interface Prato {
    fun preparar(): String
}
Agora, vamos criar uma classe abstrata PratoBase que implementa a interface Prato e define alguns métodos comuns a todos os pratos, como cortarIngredientes, cozinhar, etc:

abstract class PratoBase : Prato {

    override fun preparar(): String {
        val ingredientes = cortarIngredientes()
        val cozido = cozinhar(ingredientes)
        return servir(cozido)
    }

    protected abstract fun cortarIngredientes(): List<String>
    protected abstract fun cozinhar(ingredientes: List<String>): String
    protected abstract fun servir(prato: String): String
}
A classe PratoBase é abstrata, o que significa que não pode ser instanciada diretamente, mas fornece uma implementação padrão do método preparar, que será usado por todas as classes concretas que herdam dela.

Agora podemos criar algumas classes concretas que herdam de PratoBase e implementam os métodos abstratos:



Salada.kt

class Salada : PratoBase() {

    override fun cortarIngredientes(): List<String> {
        return listOf("alface", "tomate", "cebola", "azeitona", "pepino")
    }

    override fun cozinhar(ingredientes: List<String>): String {
        return ingredientes.joinToString(separator = ", ")
    }

    override fun servir(prato: String): String {
        return "Salada de $prato"
    }
}


Sopa.kt

class Sopa : PratoBase() {

    override fun cortarIngredientes(): List<String> {
        return listOf("cenoura", "batata", "cebola", "alho", "tomate")
    }

    override fun cozinhar(ingredientes: List<String>): String {
        return ingredientes.joinToString(separator = ", ")
    }

    override fun servir(prato: String): String {
        return "Sopa de $prato"
    }
}
Observe que cada classe concreta herda de PratoBase e implementa os métodos abstratos cortarIngredientes, cozinhar e servir de acordo com as suas particularidades.

Por fim, vamos implementar a arquitetura MVVM para exibir os pratos em uma lista no aplicativo. Primeiro, criamos a classe PratoViewModel que gerencia os dados da lista de pratos:

class PratoViewModel : ViewModel() {
    val pratos: LiveData<List<String>> get() = _pratos
    private val _pratos = MutableLiveData<List<String>>()

    init {
        val listaPratos = listOf(
            Salada().preparar(),
            Sopa().preparar(),
            Massa().preparar(),
        )
        _pratos.postValue(listaPratos)
    }
}
A classe PratoViewModel tem uma propriedade pratos que retorna uma LiveData contendo a lista de pratos, e um _pratos privado que armazena os dados. No construtor, criamos uma lista de pratos e a armazenamos na propriedade _pratos.

Em seguida, criamos a classe PratoAdapter que exibe a lista de pratos em um RecyclerView:

class PratoAdapter(val pratos: List<String>) : RecyclerView.Adapter<PratoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PratoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prato, parent, false)
        return PratoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PratoViewHolder, position: Int) {
        holder.bind(pratos[position])
    }

    override fun getItemCount(): Int {
        return pratos.size
    }
}

class PratoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvNomePrato: TextView = itemView.findViewById(R.id.tvNomePrato)

    fun bind(prato: String) {
        tvNomePrato.text = prato
    }
}
A classe PratoAdapter é responsável por criar as visualizações de cada item da lista de pratos (onCreateViewHolder) e vincular os dados a essas visualizações (onBindViewHolder). A classe PratoViewHolder é usada para armazenar e exibir as visualizações de cada item da lista.

Por fim, criamos a classe PratoFragment que exibe a lista de pratos em um RecyclerView usando o PratoAdapter:

class PratoFragment : Fragment() {

    private val viewModel: PratoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prato, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.pratos.observe(viewLifecycleOwner) { pratos ->
            recyclerView.adapter = PratoAdapter(pratos)
        }

        return view
    }
}
A classe PratoFragment é responsável por inflar o layout do fragmento (onCreateView), criar o RecyclerView e configurar o adaptador para exibir a lista de pratos. Observe que usamos a classe PratoViewModel para obter a lista de pratos e atualizar o adaptador sempre que a lista for alterada.

Assim, aplicamos o Princípio do Aberto/Fechado (OCP) em nossa implementação de um chef de cozinha em Kotlin Android e arquitetura MVVM para torná-la mais flexível e fácil de manter. Agora, se quisermos adicionar um novo tipo de prato, basta criar uma nova classe que herde de PratoBase e implemente seus métodos abstratos. A lista de pratos será atualizada automaticamente graças à arquitetura MVVM e ao uso do PratoViewModel.

Lembrando que a implementação do Princípio do Aberto/Fechado é apenas uma parte do desenvolvimento de software de qualidade e existem outros princípios SOLID que também devem ser considerados. Além disso, a arquitetura MVVM é apenas uma das muitas opções disponíveis para estruturar um aplicativo Android. É importante escolher a abordagem que melhor se adapta às necessidades do projeto em questão.
