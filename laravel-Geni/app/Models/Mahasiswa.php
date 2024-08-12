<?php

namespace App\Models;

use Attribute;
use Illuminate\Database\Eloquent\Casts\Attribute as CastsAttribute;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Mahasiswa extends Model
{
    use HasFactory;

    protected $table = 'mahasiswas';
    protected $primaryKey = 'idmahasiswa';
    public $incrementing = true;

    protected $fillable = [
        'namamahasiswa',
        'nim',
        'alamat',
        'gender',
        'agama',
        'usia',
        'image',
    ];

    protected $cats = [
        'gender' => 'string',
    ];

    protected function image(): CastsAttribute 
    {
        return  CastsAttribute::make(
            get: fn ($image) => asset('/storage/mahasiswa/'.$image)
        );
    }
}
