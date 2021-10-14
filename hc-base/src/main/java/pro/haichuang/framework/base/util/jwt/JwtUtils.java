package pro.haichuang.framework.base.util.jwt;

import io.jsonwebtoken.*;
import org.springframework.lang.NonNull;

/**
 * JWT工具类
 *
 * <p>该类为JWT工具类, 对于JWT的操作一律使用此类
 *
 * @author JiYinchuan
 * @version 1.0.0.211009
 * @see JwtPayload
 * @since 1.0.0.211009
 */
public class JwtUtils {

    /**
     * 生成 JWT
     *
     * @param payload JWT 载荷
     * @param secret  密钥
     * @return JWT字符串
     * @since 1.0.0.211009
     */
    @NonNull
    public static String generateJwt(@NonNull JwtPayload payload, @NonNull String secret) {
        return Jwts.builder()
                .setId(payload.getJti())
                .setIssuer(payload.getIss())
                .setIssuedAt(payload.getIat())
                .setExpiration(payload.getExp())
                .setNotBefore(payload.getNbf())
                .setAudience(payload.getAud())
                .setSubject(payload.getSub())
                .claim(JwtPayload.USER_ID, payload.getUserId())
                .claim(JwtPayload.INTERNAL, payload.isInternal())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 解析JwtToken
     *
     * @param token  JwtToken
     * @param secret 密钥
     * @return DecodedJWT字符串
     * @throws ExpiredJwtException   JWT过期异常
     * @throws MalformedJwtException JWT解析失败异常
     * @throws SignatureException    JWT格式错误异常
     * @since 1.0.0.211009
     */
    @NonNull
    public static JwtPayload parseJwtToken(@NonNull String token, @NonNull String secret)
            throws ExpiredJwtException, MalformedJwtException, SignatureException {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setJti(claims.getId());
        jwtPayload.setIss(claims.getIssuer());
        jwtPayload.setIat(claims.getIssuedAt());
        jwtPayload.setExp(claims.getExpiration());
        jwtPayload.setNbf(claims.getNotBefore());
        jwtPayload.setAud(claims.getAudience());
        jwtPayload.setSub(claims.getSubject());
        jwtPayload.setUserId(claims.get(JwtPayload.USER_ID, Long.class));
        jwtPayload.setInternal(claims.get(JwtPayload.INTERNAL, Boolean.class));
        return jwtPayload;
    }
}
