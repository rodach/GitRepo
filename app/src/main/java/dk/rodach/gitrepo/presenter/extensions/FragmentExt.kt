package dk.rodach.gitrepo.presenter.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

//This is from https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(modelClass: Class<T>, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this).get(modelClass)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.viewModelActivity(modelClass: Class<T>, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(requireActivity()).get(modelClass)
    vm.body()
    return vm
}