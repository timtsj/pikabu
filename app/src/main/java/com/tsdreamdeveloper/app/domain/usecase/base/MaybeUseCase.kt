package com.tsdreamdeveloper.app.domain.usecase.base

import io.reactivex.Maybe

abstract class MaybeUseCase<in PARAMS, RESULT> {

    fun execute(parameters: PARAMS) = build(parameters)

    protected abstract fun build(parameters: PARAMS): Maybe<RESULT>
}

