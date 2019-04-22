package br.com.planoleitura.tutorial

import agency.tango.materialintroscreen.MaterialIntroActivity
import agency.tango.materialintroscreen.SlideFragmentBuilder
import android.os.Bundle
import br.com.planoleitura.R

class TutorialActivity : MaterialIntroActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableLastSlideAlphaExitTransition(true)

        addSlide(SlideFragmentBuilder()
            .backgroundColor(R.color.colorPrimary)
            .buttonsColor(R.color.colorAccent)
            .image(R.drawable.ic_tuto_welcome)
            .title(getString(R.string.tutorial_first_title))
            .description(getString(R.string.tutorial_first_message))
            .build()
        )

        addSlide(SlideFragmentBuilder()
            .backgroundColor(R.color.colorPrimary)
            .buttonsColor(R.color.colorAccent)
            .image(R.drawable.ic_tuto_plans)
            .title(getString(R.string.tutorial_second_title))
            .description(getString(R.string.tutorial_second_message))
            .build()
        )

        addSlide(SlideFragmentBuilder()
            .backgroundColor(R.color.colorPrimary)
            .buttonsColor(R.color.colorAccent)
            .image(R.drawable.ic_tuto_bible)
            .title(getString(R.string.tutorial_third_title))
            .description(getString(R.string.tutorial_third_message))
            .build()
        )
    }
}
