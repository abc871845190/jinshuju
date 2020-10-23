package com.example.jinshuju.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class JwtUtils {

    //签名秘钥
    private static String PRIVATE_KEY = "";
    //签发主体
    private static String Issuer = "jinshuju";
    //过期时间
    private static long EXPIRATION_TIME = Constants.TimeValue.ONE_HOUR * 2 * 1000;
    //签名算法
    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * 创建jwt
     *
     * @param claims map集合，载荷
     * @param time   过期的时长
     * @return
     */
    public static String createJWT(Map<String, Object> claims, long time) {
        JwtUtils.EXPIRATION_TIME = time;
        return createJWT(claims);
    }

    public static String createRefreshJWT(String id, long time) {
        JwtUtils.EXPIRATION_TIME = time;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //设置签发主体
                .setIssuer(Issuer)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(id)
                //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                //.setSubject(subject)
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, getKey());
        //计算过期时间
        if (EXPIRATION_TIME >= 0) {
            long expMillis = nowMillis + EXPIRATION_TIME;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //返回token
        return builder.compact();
    }

    public static String createJWT(Map<String, Object> claims) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //设置签发主体
                .setIssuer(Issuer)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                //.setId(id)
                //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                //.setSubject(subject)
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, getKey());
        //判断是否为空
        if (claims != null) {
            //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
            builder.setClaims(claims);
        }

        //计算过期时间
        if (EXPIRATION_TIME >= 0) {
            long expMillis = nowMillis + EXPIRATION_TIME;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        //返回token
        return builder.compact();
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {

        Claims claims = Jwts
                //得到DefaultJwtParser
                .parser()
                //设置签名的秘钥
                .setSigningKey(getKey())
                //设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 加密签名
     *
     * @return
     */
    public static Key getKey() {
        //加密，里面的字符串可自行定义
        byte[] bytes = DatatypeConverter.parseBase64Binary("jinshuju");
        Key key = new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
        return key;
    }
}
