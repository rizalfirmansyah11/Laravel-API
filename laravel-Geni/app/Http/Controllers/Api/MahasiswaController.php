<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\MahasiswaResource;
use App\Models\Mahasiswa;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class MahasiswaController extends Controller
{
    //get all mahasiswwa
    public function index()
    {
        $mahasiswa = Mahasiswa::all();

        return new MahasiswaResource(true, 'List Data Mahasiswa', $mahasiswa);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'namamahasiswa'              => 'required|string|max:255',
            'nim'                        => 'required|string|max:11|unique:mahasiswas',
            'alamat'                     => 'required|string|max:255',
            'gender'                     => 'required|string|max:255',
            'agama'                      => 'required|string|max:50',
            'usia'                       => 'required|string|max:3',
            // 'image'                      => 'required|image|mimes:jpeg,png,jpg,gif,svg|max:2048',

             ],
            [
                'namamahasiswa.required'        => "nama harus diisi",
                'nim.required'                  => "nim harus diisi",
                'nim.unique'                    => "nim tisak boleh sama",
                'nim.max'                       => "nim tidak boleh lebih dari 11 digit",
                'alamat.required'               => "alamat harus diisi",
                'gender.required'               => "gender harus diisi",
                'agama.required'                => "agama harus diisi",
                'usia.required'                 => "usia harus diisi",
                // 'image.required'                => "image harus diisi",
                'nim.max'                       => "usia tidak lebih dari 3 digit",
            ]);

            //upload image
            // $image = $request->file('image');
            // $image->storeAs('public/mahasiswa', $image->hashName());

            if ($validator->fails()){
                $response["error"]  = TRUE;
                $response["success"]  = 0;
                $response["message"]  = $validator->errors()->first();

            }else{
                $mahasiswa = new Mahasiswa;
                $mahasiswa->namamahasiswa   = $request->namamahasiswa;
                $mahasiswa->nim             = $request->nim;
                $mahasiswa->alamat          = $request->alamat;
                $mahasiswa->gender          = $request->gender;
                $mahasiswa->agama           = $request->agama;
                $mahasiswa->usia            = $request->usia;
                // $mahasiswa->image           = $image->hashName();
                $data       = $mahasiswa->save();
            };

            if($data !== false)
            {
                $response["error"]      = FALSE;
                $response["success"]      = 1;
                $response["messae"]      = "Data Berhasil Disimpan";
            }else
            {
                $response["error"]      = TRUE;
                $response["success"]      = 0;
                $response["messae"]      = "Data Gagal Disimpan";
            }
            echo json_encode($response);
    }

    public  function cari(Request $request)
    {
        $mahasiswa = Mahasiswa::where('namamahasiswa', 'like', '%'.$request->cari.'%')->get();

        return new MahasiswaResource(true, 'List Data Mahasiswa', $mahasiswa);
    }
}
