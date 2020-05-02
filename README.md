# DaggerExample
Dagger test

### First Session (Basic usage of dagger)
---
Say we have a fragment need Repository and we want to provide it using Dagger, <br>
the Repository needed 3rd party library and we also will use Dagger to provide it, and it'll be like: <br>

> Property injection <br>
Repo variable <-- Fragment <-- RepoComponent <-- ThirdPartyModule <-- ThirdPartyLibrary

<br>

**Fragment**
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

**Component** <br>
Creating RepoComponent to inject to Repo property that need ThirdPartyLibrary module
```kotlin
@Component(modules = [ThirdPartyLibraryModule::class]) // <-- Providing ThirdPartyLibrary
interface RepoComponent {
    fun injectTo(fragment: FirstFragment) // <-- Inject to target Fragment, there will be Repo property, which need ThirdPartyLibrary
}
```

**Module** <br>
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

