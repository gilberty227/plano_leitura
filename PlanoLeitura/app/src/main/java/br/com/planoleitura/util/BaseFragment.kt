package br.com.planoleitura.util

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.planoleitura.R

@Suppress("MemberVisibilityCanBePrivate")
open class BaseFragment : Fragment() {

    protected var mContext: Context? = null
    protected var mSaveStateExecuted = false

    val isActivityValid: Boolean
        get() = activity != null && !activity!!.isFinishing

    val nonNullArguments: Bundle
        get() = if (arguments == null) Bundle() else arguments!!

    protected val isBaseActivity: Boolean
        get() = isActivityValid && activity is BaseActivity

    protected val actionBar: ActionBar?
        get() = if (isBaseActivity) (activity as BaseActivity).supportActionBar else null

    private val stackedFragments: List<Fragment>?
        get() = if (fragmentManager != null) fragmentManager!!.fragments else null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    protected fun clearBackStack() {
        val manager = fragmentManager

        while ((manager?.backStackEntryCount?:0) > 0)
            manager?.popBackStackImmediate()
    }

    protected fun switchToFragment(frag: Fragment,
        containerId: Int, addToBackStack: Boolean, replace: Boolean = false) {
        if (isActivityValid) {
            val fm = activity!!.supportFragmentManager
            val fragment = fm.findFragmentById(containerId)

            val ft = fm.beginTransaction()

            if (addToBackStack)
                ft.addToBackStack(frag.javaClass.name)

            if (fragment == null || addToBackStack && !replace) {
                ft.add(containerId, frag)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commitAllowingStateLoss()
            } else {
                ft.replace(containerId, frag)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commitAllowingStateLoss()
            }
        }
    }

    protected fun setActionBar(toolbar: Toolbar, displayHomeAsUpEnabled: Boolean) {
        if (isBaseActivity) {
            (activity as BaseActivity).setSupportActionBar(toolbar)
            (activity as BaseActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        }
    }

    protected fun setActionBarDisplayTitleEnabled(displayTitle: Boolean) {
        actionBar?.setDisplayShowTitleEnabled(displayTitle)
    }

    protected fun setActionBarBackButtonEnabled(displayButton: Boolean) {
        actionBar?.setHomeButtonEnabled(displayButton)
        actionBar?.setDisplayHomeAsUpEnabled(displayButton)
        actionBar?.setDisplayShowHomeEnabled(displayButton)
    }

    protected fun setActionBarVisibility(visibility: Boolean) {
        if (activity != null) {
            if (visibility)
                (activity as BaseActivity).supportActionBar?.show()
            else
                (activity as BaseActivity).supportActionBar?.hide()
        }
    }

    protected fun setActionBarTitle(titleResId: Int) {
        setActionBarTitle(getString(titleResId))
    }

    protected fun setActionBarTitle(title: String) {
        actionBar?.title = title
    }

    fun enableToolbarBackPress(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    fun dismissFragmentDialogs() {
        stackedFragments?.let { dismissInternal(it) }
    }

    private fun dismissInternal(fragments: List<Fragment>) {
        for (fragment in fragments) {
            if (fragment is DialogFragment) {
                fragment.dismissAllowingStateLoss()
            }
        }
    }
}