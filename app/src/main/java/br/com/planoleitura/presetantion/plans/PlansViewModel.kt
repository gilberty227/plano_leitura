package br.com.planoleitura.presetantion.plans

import androidx.lifecycle.ViewModel
import br.com.planoleitura.domian.use_case.PlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlansViewModel @Inject constructor(
    private val useCase: PlanUseCase) : ViewModel(){
}