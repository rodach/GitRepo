package dk.rodach.gitrepo.presenter.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dk.rodach.gitrepo.R
import dk.rodach.gitrepo.presenter.extensions.inTransaction
import dk.rodach.gitrepo.presenter.screens.main.TopReposFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.inTransaction {
            add(R.id.container, TopReposFragment.newInstance())
        }
    }
}
