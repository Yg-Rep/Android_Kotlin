package com.example.lottoexample0515

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue=Volley.newRequestQueue(applicationContext)

        val etNum:EditText=findViewById(R.id.etNum) //회차적기
        val btnSearch:Button=findViewById(R.id.btnSearch) //제출
        val tvCnt:TextView=findViewById(R.id.tvCnt) // 회차
        val tvNumber:TextView=findViewById(R.id.tvNumber)
        val tvBonus:TextView=findViewById(R.id.tvBonus)
        // id 부여
        // findViewbyid진행
        //btn클릭시 EditText에 적혀있는 회차에 대한 로또번호가져오기


            btnSearch.setOnClickListener {
                val cnt= etNum.text.toString() //사용자가 입력한 회차
                val url="https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=$cnt"

                val request=StringRequest(
                    Request.Method.GET,
                    url,
                    {val json1=JSONObject(it)
                            val drwtNo1=json1.getString("drwtNo1")
                            val drwtNo2=json1.getString("drwtNo2")
                            val drwtNo3=json1.getString("drwtNo3")
                            val drwtNo4=json1.getString("drwtNo4")
                            val drwtNo5=json1.getString("drwtNo5")
                            val drwtNo6=json1.getString("drwtNo6")
                            val bnusNo=json1.getString("bnusNo")
                        tvCnt.text="$cnt 회차 로또 번호"
                        tvNumber.text="$drwtNo1,$drwtNo2,$drwtNo3,$drwtNo4,$drwtNo5,$drwtNo6"
                        tvBonus.text="보너스 $bnusNo"
                    },
                    {
                        Toast.makeText(this@MainActivity,"실패",Toast.LENGTH_SHORT).show()
                    }
                )
                request.setShouldCache(false)
                queue.add(request)
            }
    // url 준비(https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=861 $ (회차 숫자만바뀜)
        // volley Library 가져오기 현재프로젝트에 적용하기 (통신을위해),인터넷 권한주기 Manifest file
        // Request 버튼을 눌렀을때 RequestQueue어플이 실행될때 만들어지게
        // Request --> StringRequest(Get,url,성공-->TextView로또번호setting,실패 토스트)
        // 캐시메모리까지 정리해주자
        //requestQueue에 Request를 더해주자!

        //로또 Api json--> drwtNO1~ drwtNO6, bnusNo




    }
}