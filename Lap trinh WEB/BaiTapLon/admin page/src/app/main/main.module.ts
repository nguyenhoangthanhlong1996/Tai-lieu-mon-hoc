import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MainComponent} from './main.component';
import {UserCreateComponent} from './users/user-create/user-create.component';
import {UserListComponent} from './users/user-list/user-list.component';
import {RouterModule} from '@angular/router';
import {DataTablesModule} from 'angular-datatables';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AlertDialogComponent} from '../alert-dialog/alert-dialog.component';
import {UserInfoDialogComponent} from './users/user-info-dialog/user-info-dialog.component';
import {MaterialModule} from '../material/material.module';
import { UserChangeInfoComponent } from './users/user-change-info/user-change-info.component';
import { UserChangePasswordComponent } from './users/user-change-password/user-change-password.component';
import {ComfirmDialogComponent} from '../comfirm-dialog/comfirm-dialog.component';
import { CompanyInfoChangeComponent } from './company-info/company-info-change/company-info-change.component';
import {CKEditorModule} from 'ckeditor4-angular';
import { CategoryListComponent } from './categories/category-list/category-list.component';
import { CategoryCreateComponent } from './categories/category-create/category-create.component';
import { CategoryEditComponent } from './categories/category-edit/category-edit.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductCreateComponent } from './products/product-create/product-create.component';
import { ProductEditComponent } from './products/product-edit/product-edit.component';
import {Select2Module} from 'ng2-select2';
import { UnitCreateComponent } from './units/unit-create/unit-create.component';
import { UnitEditComponent } from './units/unit-edit/unit-edit.component';
import { UnitListComponent } from './units/unit-list/unit-list.component';
import { ProductInfoDialogComponent } from './products/product-info-dialog/product-info-dialog.component';


@NgModule({
    declarations: [
        MainComponent,
        UserCreateComponent,
        UserListComponent,
        UserChangeInfoComponent,
        AlertDialogComponent,
        ComfirmDialogComponent,
        UserInfoDialogComponent,
        UserChangeInfoComponent,
        UserChangePasswordComponent,
        CompanyInfoChangeComponent,
        CategoryListComponent,
        CategoryCreateComponent,
        CategoryEditComponent,
        UnitCreateComponent,
        UnitEditComponent,
        UnitListComponent,
        ProductListComponent,
        ProductCreateComponent,
        ProductEditComponent,
        ProductInfoDialogComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        DataTablesModule,
        MaterialModule,
        CKEditorModule,
        Select2Module
    ],
    exports: [
        MainComponent
    ]
})
export class MainModule {
}
