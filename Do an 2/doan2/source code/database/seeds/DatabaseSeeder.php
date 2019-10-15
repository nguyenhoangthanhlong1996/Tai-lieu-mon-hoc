<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        $this->call(PermissionsSeeder::class);
        $this->call(UsersSeeder::class);
    }
}

class PermissionsSeeder extends Seeder
{
    public function run() {
        DB::table('permissions')->insert([
            ['id' => '1', 'type' => 'Super Admin', 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')],
            ['id' => '2', 'type' => 'Admin', 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')],
            ['id' => '3', 'type' => 'Member', 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')]
        ]);
    }
}

class UsersSeeder extends Seeder
{
    public function run() {
        DB::table('users')->insert([
            ['username' => 'long', 'password' => bcrypt('long'), 'name' => 'Nguyễn Hoàng Thanh Long', 'id_permission' => 1, 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')],
            ['username' => 'thuy', 'password' => bcrypt('thuy'), 'name' => 'Nguyễn Ngọc Như Thùy', 'id_permission' => 2, 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')],
            ['username' => 'son', 'password' => bcrypt('son'), 'name' => 'Nguyễn Thanh Sơn', 'id_permission' => 2, 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')],
            ['username' => 'hoa', 'password' => bcrypt('hoa'), 'name' => 'Nguyễn Thị Hoa', 'id_permission' => 2, 'created_at' => date('Y-m-d H:i:s'), 'updated_at' => date('Y-m-d H:i:s')]
        ]);
    }
}
