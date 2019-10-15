<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Support\Facades\Auth;

class AdminLogin
{
    
    public function handle($request, Closure $next)
    {
        if (Auth::check()) {
            $user = Auth::user();
            if ($user->isSuperAdmin() || $user->isAdmin()) {
                $response = $next($request);
                return $response
                    ->header('Cache-Control','nocache, no-store, max-age=0, must-revalidate')
                    ->header('Pragma','no-cache')
                    ->header('Expires','Fri, 01 Jan 1990 00:00:00 GMT');
            } else {
                return redirect()->route('admin.login.index');
            }
        } else {
            return redirect()->route('admin.login.index');
        }
    }
}
