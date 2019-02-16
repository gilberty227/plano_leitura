package br.com.planoleitura.util

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem

abstract class BaseActivity : AppCompatActivity() {

    var isStateSaved = false
        protected set
    var isActivityResumed = false
        protected set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()

        isActivityResumed = false
    }

    override fun onResume() {
        super.onResume()

        isActivityResumed = true
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        var outStates = outState
        isStateSaved = true

        if (outStates == null)
            outStates = Bundle()

        outStates.putBoolean(ARG_CREATED_BEFORE, true)
        super.onSaveInstanceState(outStates)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        isStateSaved = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                try {
                    onBackPressed()
                } catch (e: Exception) {
                    Log.e("BaseActivity", "Child class: " + this.javaClass.simpleName, e)
                    throw e
                }

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    protected fun replaceFragment(frag: Fragment, @IdRes container: Int, addToBackStack: Boolean) {
        addFragment(frag, container, addToBackStack, true)
    }

    @JvmOverloads
    protected fun addFragment(
        frag: Fragment, @IdRes container: Int,
        addToBackStack: Boolean,
        forceReplace: Boolean = true
    ) {
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(container)

        val ft = fm.beginTransaction()

        if (addToBackStack)
            ft.addToBackStack(frag.javaClass.name)

        if (isFinishing || isDestroyed)
            return

        if ((fragment == null || addToBackStack) && !forceReplace) {
            ft.add(container, frag, frag.javaClass.name)
            ft.commitAllowingStateLoss()
        } else {
            ft.replace(container, frag, frag.javaClass.name)
            ft.commitAllowingStateLoss()
        }
    }


    protected fun clearFragmentBackStack() {
        val manager = supportFragmentManager
        if (manager != null) {
            while (manager.backStackEntryCount > 0)
                manager.popBackStackImmediate()
        }
    }

    protected fun getCurrentFragment(@IdRes containerId: Int): Fragment? {
        val fm = supportFragmentManager
        return fm.findFragmentById(containerId)
    }

    companion object {

        val ARG_CREATED_BEFORE = "arg_created_before"

        val TAG = BaseActivity::class.java.simpleName
    }
}