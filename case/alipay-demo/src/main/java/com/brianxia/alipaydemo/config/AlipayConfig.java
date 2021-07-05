package com.brianxia.alipaydemo.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/5 10:54
 */
public class AlipayConfig {

    //应用私钥
    public static final String APP_PRI_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCtlFB/Cgmv/QXT/K9g46TEbwYfzj/JG1MFxqAWO+GKKfQoCQohZGkTFsnL4PJG2ayAvDLQ9boambB2W/lOlnbv3ILK+dHPWHf0DXCM4KxYl2JIZCh/d882UoTOdXGtRsyPtgqbp4JtEzOJwJJHwvVh6ujrNhrPSe3pCipTLiUa29zKUIo42ju3PQ6AyKEsD+KD6AU9kKwxQ/OcfCsKx1dfu9MpIasxOJFJ+Ht40mDEjLOvSL/gEfJ1M6J4m9nVtLhysHyyeDCuafaeOZysUlBlrC1eEmPjClnhdcqO4Espj0teYcD4VMz9U9oF2eZvEcPYWo1aOXFsOWfBgJw7di2pAgMBAAECggEACeG+r2zP3mAGO+pPxbFQCQrVC2x/fyB6GoMTTBC+p1H6H2lcVUhVHwwWEvBMerIbuvx73B+RJ8CGJkbYK5wvSzp/i7SrOCWuNoupU6vaw/RiWQKyt4cHme0auHJBNX8pWCkh83iW2UTVk0sOSN2VgKyR9AoRfqmTzMeSX0fqPtwbcwnCvhhk+aQE3n0scRC+j1FhSBOznei/HhrcrdjO9Jduk/oEXLJe4kdIdsZgNnsrRq5Ps6/6TSuDwHQJ39HgxZjvqxA2Gunrf/tuJUX1l/nAZeeDhUggEXK1ZV/dAVtbHSjJxH0BU/Hc6oiQh6+IFSZ0KjEDMqJnVGweRXmSAQKBgQDZv7A3JgTc4qccR5Ynm/F2rE3+4u8HRDzK2OevzQgRlTN2p+nac6lQQBDXN3IoCKgs0ER4tyBG5ys6kx4pg0wr8Jni0dOOuF09lqKUwQ0I+WKXqhv6mAHCk2C+9n95ACOKSyZglI7BTFV0IlNd16LkV8zeoSqpaT3E2CxmJOZr6QKBgQDMEkvxprAbp5M8OTG5kE/ZajwM+m9wiAC1KLgJ13p3TaDGwqKf6B5pi5MOzzZvtC6CfXm4KvSwn0SEcTQ/vEwaGHE47O7uLtxSQIttJT4VaFpNEGRe8kVb+5+FCW/SSK8rdefyUDvwCxI8sWdyaXUQci0e3O5MzfiwPAwMz2tbwQKBgBn8Sz45lkj573FrhZrSfF1qMKCjWl0moo6elf+f2z+l649UeI7uUJ1YF+pnHoNyjQdUD2fMitiLS9hgqC/SEGwJ+YNdAP8+NzpbWKmh5DlW8ZIh5dbg/wzGY7xGVG4POy6My1apQ+QDhQsn8YT4b+MKK1PCLgIrogh0t8+0xZ7JAoGAZY0xV6aweoKQpWZ81S/4XGQeUAMME77KQ1xpARTGr7nkqSRQUyzqfXFkk/kPyNSsVGlmefx+WapYKWRAMPteD5pcNYzKdaLCJvhRqdXfqD2OukzO9eSwGU8FGeoRSF69NO5up0nAGfsYbzWFGg1aoP2AcJxWxce12tV21aUecsECgYEAxQttrsdLVcTBYJMwwQpJgIv2aTFf/BqWAqJTt4idDNyeQgDeBcAQsyyM6qeGUcaC1vLqblTG+/SRHlEXKz36B2Dn8ODB0zScGxbhHV6lKhAuUyHY7cwXQESUvkfHbTVSG55SHrEbuKLJPLXukgadfkXjsGjyRULZ4RCKbXKY5Go=";
    //支付宝公钥
    public static final String ALIPAY_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjAOBYfljeQrW13eHK1gnKsQmD4AmUPP3m9OpIGb20AfxSoSwMJDvLd7FHzuyZQl6I1UUMgnMb/FzEOzDUaw7KWtFoM7BGh7vejhL7+mBUnc6Diy03kgARUZEteNqydsNQawHLwI5qC3fazw5R+COqwdx/AaoyXlkPJ8kzRWnOfHAUI1SQS/hRgAe7lj5wfX108xcPqqZzI9goKlIC/iGxwGJsOACx7VZiBvs8jv8aEAoIxqqRIIGw7/eHpntF5cKk+LyLpb2/Au/yN/B89fFiEcGTmO+TLjX3XoOi0+Qpf6fzTAaM2vDMjV8QCTAHtCLGBTa03J8LntVP9SZb5SPNQIDAQAB";


    /**
     * 支付宝网关地址  TODO
     */
    public static final  String PAY_GATEWAY="https://openapi.alipaydev.com/gateway.do";


    /**
     * 支付宝 APPID TODO
     */
    public static final  String APPID="2016110300788890";


    /**
     * 签名类型
     */
    public static final  String SIGN_TYPE="RSA2";


    /**
     * 字符编码
     */
    public static final  String CHARSET="UTF-8";


    /**
     * 返回参数格式
     */
    public static final  String FORMAT="json";


    /**
     * 构造函数私有化
     */
    private AlipayConfig(){

    }


    private volatile static AlipayClient instance = null;




}