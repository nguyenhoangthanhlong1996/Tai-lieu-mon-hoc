<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Support\Facades\Auth;

class User
{
    
    public function handle($request, Closure $next)
    {
        if (Auth::check()) {
            $user = Auth::user();   
            $routeName = $request->route()->getName();
            $idAccount = $request->route('account');
            if (
                $user->isSuperAdmin() || 
                ($user->isAdmin() && (($routeName == 'admin.account.index') || (($routeName == 'admin.account.edit' || $routeName == 'admin.account.update') &&  $idAccount == $user->id)))
            ){
                return $next($request);
            } else {
                return redirect()->route('admin.account.index');
            }
        } else {
            return redirect()->route('admin.account.index');
        }
    }
}
