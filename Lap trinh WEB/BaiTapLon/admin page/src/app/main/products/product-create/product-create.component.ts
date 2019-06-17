import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {CategoryService} from '../../../services/category.service';
import {MatDialog} from '@angular/material';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {Select2OptionData} from 'ng2-select2';
import {UnitService} from '../../../services/unit.service';
import {ProductService} from '../../../services/product.service';

@Component({
    selector: 'app-product-create',
    templateUrl: './product-create.component.html',
    styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

    formProduct: FormGroup;
    listCategories: Array<Select2OptionData> = [];
    listUnits: Array<Select2OptionData> = [];
    currentEventCoverImage: any;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private categoryService: CategoryService,
                private unitService: UnitService,
                private productService: ProductService,
                private matDialog: MatDialog) {
    }

    ngOnInit() {
        // Khởi tạo form
        this.formProduct = this.formBuilder.group({
            categoryId: ['', Validators.required],
            unitId: ['', Validators.required],
            name: ['', Validators.required],
            price: ['', Validators.required],
            quantityRemaining: ['', Validators.required],
            outOfStockFlag: [false],
            description: [''],
            posts: [''],
            tags: [''],
            coverImage: [''],
        });
        // Lấy danh sách danh mục
        this.categoryService.list()
            .subscribe(response => {
                if (response.code === 200) {
                    const mappedData = response.data.map(item => ({id: item.id, text: item.name}));
                    mappedData.unshift({id: '', text: '--Chọn--'});
                    this.listCategories = mappedData;
                }
            }, error => {
                console.log(error);
            });
        // Lấy danh sách đơn vị tính
        this.unitService.list()
            .subscribe(response => {
                if (response.code === 200) {
                    const mappedData = response.data.map(item => ({id: item.id, text: item.name}));
                    mappedData.unshift({id: '', text: '--Chọn--'});
                    this.listUnits = mappedData;
                }
            }, error => {
                console.log(error);
            });
    }

    chooseCoverImage(event: any): void {
        this.currentEventCoverImage = event;
        const fileList = event.target.files;
        const file: File = fileList.item(0);
        const reader = new FileReader();
        reader.onload = (evt: any) => {
            this.formProduct.get('coverImage').setValue(evt.target.result);
        };
        reader.readAsDataURL(file);
    }

    removeCoverImage() {
        if (this.currentEventCoverImage !== undefined) {
            this.currentEventCoverImage.srcElement.value = null;
        }
        this.formProduct.get('coverImage').setValue('');
    }

    onSubmit(): void {
        this.productService.create(this.formProduct.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    this.formProduct.reset({
                        categoryId: '',
                        unitId: '',
                        name: '',
                        price: '',
                        quantityRemaining: '',
                        outOfStockFlag: false,
                        description: '',
                        posts: '',
                        tags: '',
                        coverImage: '',
                    });
                    this.removeCoverImage();
                    const alertData: IAlertDialogData = {content: 'Tạo sản phẩm thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

    selectCategoryChange(e) {
        this.formProduct.get('categoryId').setValue(e.value);
    }

    selectUnitChange(e) {
        this.formProduct.get('unitId').setValue(e.value);
    }
}
