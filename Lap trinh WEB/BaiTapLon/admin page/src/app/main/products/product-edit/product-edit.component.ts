import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Select2OptionData} from 'ng2-select2';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../../services/category.service';
import {UnitService} from '../../../services/unit.service';
import {ProductService} from '../../../services/product.service';
import {MatDialog} from '@angular/material';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {IProduct} from '../../../interfaces/product/IProduct';
import {isBoolean} from 'util';

@Component({
    selector: 'app-product-edit',
    templateUrl: './product-edit.component.html',
    styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

    formProduct: FormGroup;
    listCategories: Array<Select2OptionData> = [];
    listUnits: Array<Select2OptionData> = [];
    currentEventCoverImage: any;

    constructor(private activatedRoute: ActivatedRoute,
                private router: Router,
                private formBuilder: FormBuilder,
                private categoryService: CategoryService,
                private unitService: UnitService,
                private productService: ProductService,
                private matDialog: MatDialog) {
    }

    ngOnInit() {
        // Khởi tạo form
        this.formProduct = this.formBuilder.group({
            id: ['', Validators.required],
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
        // Lấy thông tin sản phẩm
        const id = +this.activatedRoute.snapshot.paramMap.get('id');
        this.productService.detail(id)
            .subscribe(response => {
                if (response.code === 200) {
                    const productDetail: IProduct = response.data;
                    // Lấy danh sách danh mục
                    this.categoryService.list()
                        .subscribe(response1 => {
                            if (response.code === 200) {
                                const mappedData = response1.data.map(item => ({id: item.id, text: item.name}));
                                mappedData.unshift({id: '', text: '--Chọn--'});
                                this.listCategories = mappedData;
                                this.formProduct.get('categoryId').setValue(productDetail.category_id);
                            }
                        }, error => {
                            console.log(error);
                        });
                    // Lấy danh sách đơn vị tính
                    this.unitService.list()
                        .subscribe(response2 => {
                            if (response.code === 200) {
                                const mappedData = response2.data.map(item => ({id: item.id, text: item.name}));
                                mappedData.unshift({id: '', text: '--Chọn--'});
                                this.listUnits = mappedData;
                                this.formProduct.get('unitId').setValue(productDetail.unit_id);
                            }
                        }, error => {
                            console.log(error);
                        });
                    // Gán các giá trị lên form
                    this.formProduct.get('id').setValue(productDetail.id);
                    this.formProduct.get('name').setValue(productDetail.name);
                    this.formProduct.get('price').setValue(productDetail.price);
                    this.formProduct.get('quantityRemaining').setValue(productDetail.quantity_remaining);
                    this.formProduct.get('outOfStockFlag').setValue(!!+productDetail.out_of_stock_flag);
                    this.formProduct.get('description').setValue(productDetail.description);
                    this.formProduct.get('posts').setValue(productDetail.posts);
                    this.formProduct.get('tags').setValue(productDetail.tags);
                    this.formProduct.get('coverImage').setValue(productDetail.cover_image);
                } else if (response.code === 404) {
                    this.router.navigate(['/page-not-found']);
                } else {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
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
        console.log(this.formProduct.value);
        this.productService.update(this.formProduct.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    const alertData: IAlertDialogData = {content: 'Sửa sản phẩm thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                    this.router.navigate(['/product-list']);
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
