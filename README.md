# DaggerExample
Exercise of using Dagger2 from [link](https://youtu.be/ZOFzKCtuDPw) <br>

We'll do it by steps:
1. [First session](#first-session)
   - Property injection
   - Modules + Provides
   - Component basic

2. [Second session](#second-session)
   - Property injection
   - Constructor injection
   - Modules + Binds
   - Component basic
   
---

### First Session
Say we have a fragment need Repository and we want to provide it using Dagger, <br>
the Repository needed 3rd party library and we also will use Dagger to provide it, and it'll be like: <br>

> Property injection <br>
Repo variable `<--` [Fragment](#fragment) `<--` [RepoComponent](#component) `<--` [ThirdPartyModule](#module) `<--` ThirdPartyLibrary

<br>

##### Fragment
```kotlin
class FirstFragment : Fragment() {
    ...
    @Inject
    lateinit var repo: Repo // <-- need dagger to inject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerRepoComponent.create().injectTo(this) // <-- Dagger injecting
        ...
    }
    ...
}
```

**Repo Class**
```kotlin
class Repo
@Inject constructor(thirdParty: ThirdPartyLibrary) { // <-- Need Dagger to inject ThirdPartyLibrary
    val getText = thirdParty.libraryText
}
```

##### Component
Creating RepoComponent to inject to Repo property that need ThirdPartyLibrary module
```kotlin
@Component(modules = [ThirdPartyLibraryModule::class]) // <-- Providing ThirdPartyLibrary
interface RepoComponent {
    fun injectTo(fragment: FirstFragment) // <-- Inject to target Fragment, there will be Repo property, which need ThirdPartyLibrary
}
```

##### Module
Creating and providing ThirdPartyLibrary instance
```kotlin
@Module
class ThirdPartyLibraryModule {
    @Provides
    fun provideThirdPartyLibrary(): ThirdPartyLibrary {
        return ThirdPartyLibrary()
    }
}
```

---

### Second Session
We will use two kind of injection here:
1. Property injection <br>
   - we will inject ViewModel **factory** to a property
   - use `@Binds` instead `@Provides` to provide factory: [stackoverflow explanation](https://stackoverflow.com/a/52618571/12751279)
     - Binds will create object that need implementation of other class (like SecondViewModelProvider extends ViewModelProvider.Factory) 
     - Binds will also need @Inject annotation at provider class (SecondViewModelProvider)

> factory `<--` [Fragment](#fragment) `<--` [SecondFactoryComponent](#component) `<--` [SecondFactoryModule](#module) `<--` [SecondViewModelProvider](#provider-factory)

2. Constructor injection <br>
we use **Repository** in **ViewModel**'s constructor, Dagger will automatically inject repository to viewmodel when we put `@Inject` annotation to **receiver** (ViewModel) and **provider** (Repository)

> [ViewModel](#viewmodel) `<--` [Repository](#repository)

<br>

##### Fragment
```kotlin
class SecondFragment : Fragment() {
    ...
    @Inject // <-- Property injection
    lateinit var factory: SecondViewModelProvider
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerSecondFactoryComponent.create().injectTo(this) // <-- Injecting to this Fragment

        // factory = SecondViewModelProvider(repository) <-- without Dagger, this is how we it to provide Repository fo ViewModel 
        viewModel = ViewModelProvider(this, factory).get(SecondViewModel::class.java)
        ...
}
```

##### Component
```kotlin
@Component(modules = [SecondFactoryModule::class])
interface SecondFactoryComponent {
    fun injectTo(fragment: SecondFragment)
}
```

##### Module
```kotlin
/**
 * Two kind of Module
 *
 * - Class and Provides (first example)
 *   --> why? because if we need to configure the 3rdParty object
 *
 * - Abstract Class and Binds (this example)
 *   --> create the 3rdParty object with implementation needed (SecondViewModelProvider implements ViewModelProvider.Factory)
 */
@Module
abstract class SecondFactoryModule {
    @Binds
    abstract fun bindFactory(factory: SecondViewModelProvider): ViewModelProvider.Factory
}
```

##### Provider Factory
```kotlin
class SecondViewModelProvider
@Inject constructor(
    private val viewModelProvider: Provider<SecondViewModel> // <-- without Dagger, when we wants to -
                                                             // provide Repository to viewModel through constructor:
    // private val repository: Repository <-- code
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProvider.get() as T
    }

    // without Dagger, the overwrite will be written like:
    //
    // override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    // if (modelClass.isAssignableFrom(SecondViewModel::class.java)) {  <-- is able to assign from SecondViewModel
    //         return SecondViewModel(repository) as T                  <-- pass repository to ViewModel
    //     }
    //     throw IllegalArgumentException("Unknown ViewModel class")
    // }
}
```

##### ViewModel
```kotlin
class SecondViewModel
@Inject constructor( // <-- Constructor injection, will inject Repository here
    private val repository: Repository
) : ViewModel() {

    val username = repository.fetchUsername()
}
```

##### Repository
```kotlin
class Repository
@Inject constructor() { // <-- Constructor injection, will mark this as object to provide to other constructor

    private val username = MutableLiveData<String>("Value from Repository")
    fun fetchUsername() = username.value
}
```






