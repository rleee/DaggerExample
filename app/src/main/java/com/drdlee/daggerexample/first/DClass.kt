package com.drdlee.daggerexample.first

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * Internal Repository
 *
 * this class need ThirdPartyLibrary,
 * which will be provided when we inject Repo to Fragment
 *
 * flow will be:
 * Repo <- Fragment <- RepoComponent <- ThirdPartyModule <- ThirdPartyLibrary
 */
class Repo
@Inject constructor(thirdParty: ThirdPartyLibrary) {
    val getText = thirdParty.libraryText
}

/**
 * Component to create Repo class
 * and inject to target: FirstFragment
 */
@Component(modules = [ThirdPartyLibraryModule::class])
interface RepoComponent {
    fun injectTo(fragment: FirstFragment)
}

/**
 * Module to create ThirdPartyLibrary
 */
@Module
class ThirdPartyLibraryModule {
    @Provides
    fun provideThirdPartyLibrary(): ThirdPartyLibrary {
        return ThirdPartyLibrary()
    }
}

/**
 * Sample 3rd party library
 */
class ThirdPartyLibrary {
    val libraryText = "Third Wheels"
}