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

class BaseFragment : Fragment() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        mSaveStateExecuted = false
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mSaveStateExecuted = true

        super.onSaveInstanceState(outState)
    }

    protected fun clearBackStack() {
        val manager = fragmentManager

        if (manager != null) {
            while (manager.backStackEntryCount > 0)
                manager.popBackStackImmediate()
        }
    }

    @JvmOverloads
    protected fun switchToFragment(
        frag: Fragment,
        containerId: Int,
        addToBackStack: Boolean,
        replace: Boolean = false
    ) {
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

    fun dismissDialogWithTag(tag: String) {
        val fm = fragmentManager

        if (fm != null) {
            val fragment = fm.findFragmentByTag(tag)

            if (fragment != null && fragment is DialogFragment && fragment.isAdded)
                fragment.dismissAllowingStateLoss()
        }
    }

    protected fun setActionBar(toolbar: Toolbar, displayHomeAsUpEnabled: Boolean) {
        if (isBaseActivity) {
            (activity as BaseActivity).setSupportActionBar(toolbar)
            (activity as BaseActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        }
    }

    protected fun setActionBarDisplayTitleEnabled(displayTitle: Boolean) {
        val actionBar = actionBar
        actionBar?.setDisplayShowTitleEnabled(displayTitle)
    }

    protected fun setActionBarBackButtonEnabled(displayButton: Boolean) {
        val actionBar = actionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(displayButton)
            actionBar.setDisplayHomeAsUpEnabled(displayButton)
            actionBar.setDisplayShowHomeEnabled(displayButton)
        }
    }

    protected fun setActionBarVisibility(visibility: Boolean) {
        if (activity != null) {
            val actionBar = (activity as BaseActivity).supportActionBar

            if (actionBar != null) {
                if (visibility)
                    actionBar.show()
                else
                    actionBar.hide()
            }
        }
    }

    protected fun setActionBarTitle(titleResId: Int) {
        setActionBarTitle(getString(titleResId))
    }

    protected fun setActionBarTitle(title: String) {
        val actionBar = actionBar

        if (actionBar != null)
            actionBar.title = title
    }

    fun enableToolbarBackPress(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            if (activity != null)
                activity!!.onBackPressed()
        }
    }

    protected fun showDialogFragment(dialog: DialogFragment) {
        if (isActivityValid && fragmentManager != null)
            dialog.show(fragmentManager!!, dialog.javaClass.simpleName)
    }

    fun dismissFragmentDialogs() {
        val fragments = stackedFragments
        if (fragments != null)
            dismissInternal(fragments)
    }

    private fun dismissInternal(fragments: List<Fragment>) {
        for (fragment in fragments) {
            if (fragment is DialogFragment) {
                fragment.dismissAllowingStateLoss()
            }
        }
    }
}