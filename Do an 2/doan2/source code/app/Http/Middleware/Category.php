<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Support\Facades\Auth;

class Category
{
    
    public function handle($request, Closure $next)
    {
        if (Auth::check()) {
            $user = Auth::user();   
            $routeName = $request->route()->getName();
    
            if ($user->isSuperAdmin() || ($user->isAdmin() && ($routeName == 'admin.category.index'))){
                return $next($request);
            } else {
                return redirect()->route('admin.category.index');
            }
        } else {
            return redirect()->route('admin.category.index');
        }
    }
}
