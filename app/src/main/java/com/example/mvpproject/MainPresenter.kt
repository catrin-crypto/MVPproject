package com.example.mvpproject

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel = CountersModel()
) {
    fun counterClick(id: Int) {
        val nextValue = model.next(id)
        view.setButtonText(id, nextValue.toString())
    }
}