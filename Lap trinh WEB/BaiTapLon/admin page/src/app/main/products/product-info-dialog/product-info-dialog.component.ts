import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {IUser} from '../../../interfaces/user/IUser';
import {IProduct} from '../../../interfaces/product/IProduct';

@Component({
    selector: 'app-product-info-dialog',
    templateUrl: './product-info-dialog.component.html',
    styleUrls: ['./product-info-dialog.component.css']
})
export class ProductInfoDialogComponent implements OnInit {

    constructor(
        private dialogRef: MatDialogRef<ProductInfoDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: IProduct) {
    }

    ngOnInit() {
    }

}
