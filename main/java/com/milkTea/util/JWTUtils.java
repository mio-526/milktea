package com.milkTea.util;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.milkTea.bean.MUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    // 失效时间 24小时
    private static final Long EXPIRATION = 1000L * 60 * 60 * 24;


    // 密钥
    private static final String SECRET="abcdefghijklmnopqrstuvwsyz1234567890";

    public static String createToken(MUser user){
        Date expiration = new Date(new Date().getTime()+ EXPIRATION);
        Map<String, Object> map = new HashMap<>();
        map.put("alg","hs256");
        map.put("typ","JWT");
        String jsonUser = JSONUtil.toJsonStr(user);
        return JWT.create()
                .withHeader(map)
                .withClaim("user",jsonUser)
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verifyToken(String token){
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static MUser getUser(String token){
        DecodedJWT verify = verifyToken(token);
        String user = verify.getClaim("user").asString();
        return JSONUtil.parse(user).toBean(MUser.class);
    }



}
