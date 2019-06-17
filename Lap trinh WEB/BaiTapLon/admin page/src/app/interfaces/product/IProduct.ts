
export interface IProduct {
    id: number;
    category_id: number;
    category_name: string;
    unit_id: number;
    unit_name: string;
    name: string;
    price: string;
    quantity_remaining: number;
    out_of_stock_flag: boolean;
    description: string;
    posts: string;
    tags: string;
    cover_image: string;
    created_at: string;
    updated_at: string;
}
