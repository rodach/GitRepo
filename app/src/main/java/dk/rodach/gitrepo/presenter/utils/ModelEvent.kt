package dk.rodach.gitrepo.presenter.utils

class ModelEvent<out T>(private val content: T) {

    var alreadyHandled = false
        private set

    /**
     * getting content only one time
     */

    fun getNotHandledContent(): T? {
        return if (alreadyHandled) {
            null
        } else {
            alreadyHandled = true
            content
        }
    }

    /**
     * getting content in any state
     */
    fun forceContent(): T = content
}