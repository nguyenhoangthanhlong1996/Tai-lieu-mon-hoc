import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {Page404Component} from './page404/page404.component';
import {AuthGuard} from './auth.guard';
import {UserCreateComponent} from './main/users/user-create/user-create.component';
import {UserListComponent} from './main/users/user-list/user-list.component';
import {UserChangeInfoComponent} from './main/users/user-change-info/user-change-info.component';
import {UserChangePasswordComponent} from './main/users/user-change-password/user-change-password.component';
import {CompanyInfoChangeComponent} from './main/company-info/company-info-change/company-info-change.component';
import {CategoryListComponent} from './main/categories/category-list/category-list.component';
import {CategoryCreateComponent} from './main/categories/category-create/category-create.component';
import {CategoryEditComponent} from './main/categories/category-edit/category-edit.component';
import {ProductListComponent} from './main/products/product-list/product-list.component';
import {ProductCreateComponent} from './main/products/product-create/product-create.component';
import {ProductEditComponent} from './main/products/product-edit/product-edit.component';
import {UnitListComponent} from './main/units/unit-list/unit-list.component';
import {UnitCreateComponent} from './main/units/unit-create/unit-create.component';
import {UnitEditComponent} from './main/units/unit-edit/unit-edit.component';

const routes: Routes = [
    {path: 'login', component: LoginComponent, canActivate: [AuthGuard]},
    {
        path: '',
        component: MainComponent,
        canActivate: [AuthGuard],
        children: [
            // region user
            {
                path: 'user-create',
                component: UserCreateComponent
            },
            {
                path: 'user-list',
                component: UserListComponent
            },
            {
                path: 'user-change-info/:username',
                component: UserChangeInfoComponent
            },
            {
                path: 'user-change-password/:username',
                component: UserChangePasswordComponent
            },
            // endregion
            // region company-info
            {
                path: 'company-info-change',
                component: CompanyInfoChangeComponent
            },
            // endregion
            // region category
            {
                path: 'category-list',
                component: CategoryListComponent
            },
            {
                path: 'category-create',
                component: CategoryCreateComponent
            },
            {
                path: 'category-edit/:id',
                component: CategoryEditComponent
            },
            // endregion
            // region unit
            {
                path: 'unit-list',
                component: UnitListComponent
            },
            {
                path: 'unit-create',
                component: UnitCreateComponent
            },
            {
                path: 'unit-edit/:id',
                component: UnitEditComponent
            },
            // endregion
            // region product
            {
                path: 'product-list',
                component: ProductListComponent
            },
            {
                path: 'product-create',
                component: ProductCreateComponent
            },
            {
                path: 'product-edit/:id',
                component: ProductEditComponent
            },
            // endregion
        ]
    },
    {path: '**', redirectTo: 'page-not-found', pathMatch: 'full'},
    {path: 'page-not-found', component: Page404Component}
];

@NgModule({
    declarations: [],
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule]
})

export class AppRoutingModule {
}
