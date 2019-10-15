<?php
//Route amdin
Route::group(['prefix' => 'admin'], function () {
    Route::resource('login', 'Admin\LoginController', [
        'names' => [
            'index' => 'admin.login.index',
            'store' => 'admin.login.store',
            'create' => 'admin.login.create'
        ]
    ]);

    Route::group(['prefix' => '/','middleware'=>'admin'], function () {
        Route::get('/', function() {
            return redirect()->route('admin.home.index');
        });
        //trang chủ
        Route::resource('home', 'Admin\HomeController', [
            'names' => [
                'index' => 'admin.home.index'
            ]
        ]);
        //tài khoản
        Route::resource('account', 'Admin\UserController', [
            'names' => [
                'index' => 'admin.account.index',
                'create' => 'admin.account.create',
                'store' => 'admin.account.store',
                'edit' => 'admin.account.edit',
                'update' => 'admin.account.update',
                'destroy' => 'admin.account.destroy',
            ]
        ]);
        //chuyên mục
        Route::resource('category', 'Admin\CategoryController', [
            'names' => [
                'index' => 'admin.category.index',
                'create' => 'admin.category.create',
                'store' => 'admin.category.store',
                'edit' => 'admin.category.edit',
                'update' => 'admin.category.update',
                'destroy' => 'admin.category.destroy',
            ]
        ]);
        //câu hỏi
        Route::resource('question', 'Admin\QuestionController', [
            'names' => [
                'index' => 'admin.question.index',
                'create' => 'admin.question.create',
                'store' => 'admin.question.store',
                'edit' => 'admin.question.edit',
                'update' => 'admin.question.update',
                'destroy' => 'admin.question.destroy',
            ]
        ]);
    });
});

//Route front
Route::group(['prefix' => '/'], function () {
    Route::get('/', 'Front\FrontController@home')->name('front.home');
    Route::get('/option', 'Front\FrontController@option')->name('front.option');
    Route::post('/do-exam', 'Front\FrontController@doExam')->name('front.doExam');
    Route::post('/result', 'Front\FrontController@result')->name('front.result');
});