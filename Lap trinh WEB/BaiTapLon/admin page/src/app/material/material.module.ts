import {NgModule} from '@angular/core';
import {MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AlertDialogComponent} from '../alert-dialog/alert-dialog.component';
import {UserInfoDialogComponent} from '../main/users/user-info-dialog/user-info-dialog.component';
import {ComfirmDialogComponent} from '../comfirm-dialog/comfirm-dialog.component';
import {ProductInfoDialogComponent} from '../main/products/product-info-dialog/product-info-dialog.component';


@NgModule({
    declarations: [],
    imports: [],
    exports: [MatDialogModule, MatFormFieldModule, BrowserAnimationsModule, MatInputModule, MatButtonModule],
    entryComponents: [
        AlertDialogComponent,
        ComfirmDialogComponent,
        UserInfoDialogComponent,
        ProductInfoDialogComponent
    ]
})
export class MaterialModule {
}

