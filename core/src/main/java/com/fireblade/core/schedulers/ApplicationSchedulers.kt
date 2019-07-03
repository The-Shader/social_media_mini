package com.fireblade.core.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

interface ISchedulers {
    fun androidMain(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
    fun newThread(): Scheduler
    fun single(): Scheduler
}

class ApplicationSchedulers : ISchedulers {

    override fun androidMain(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

    override fun newThread(): Scheduler = Schedulers.newThread()

    override fun single(): Scheduler = Schedulers.single()
}

class TestSchedulers : ISchedulers {

    override fun androidMain(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun newThread(): Scheduler = Schedulers.trampoline()

    override fun single(): Scheduler = Schedulers.trampoline()
}