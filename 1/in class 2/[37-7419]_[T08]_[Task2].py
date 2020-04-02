from Crypto.Cipher import AES
from Crypto import Random
from Crypto.Hash import SHA256
import binascii
import random as r


def encrypt(plain, key):

    # pass  # REMOVE THIS LINE
    iv = Random.new().read(AES.block_size)
    hash_hex = hash(plain, iv)
    to_encrypt = plain + hash_hex
    cipher = AES.new(key, AES.MODE_CFB, iv)
    msg = iv + cipher.encrypt(to_encrypt)
    # TODO: Return cipher text
    return msg


def decrypt(cipher, key):
    iv = cipher[:16]
    no_iv = cipher[16:]
    aes_cipher = AES.new(key, AES.MODE_CFB, iv)
    decrypted = aes_cipher.decrypt(no_iv)
    plain = decrypted[:-64]
    received_hash = [-64]
    hashed = hash(plain.decode(), iv)
    isCorrupt = hashed == received_hash
    # pass  # REMOVE THIS LINE

    # TODO: Return plain text and the isCorrupt flag
    return plain.decode(), isCorrupt


def hash(msg, iv):

    # pass  # REMOVE THIS LINE
    msg = msg.encode() + iv
    hash_object = SHA256.new(data=msg)

    # TODO: Return hash as hex
    return hash_object.digest().hex()


if __name__ == "__main__":
    key = 'Sixteen byte key'
    plain = 'Hello from the other side'
    

    cipher = encrypt(plain, key)
    print('CFB cipher output: ', cipher.hex())
    plain, isCorrupt = decrypt(cipher, key)
    print('CFB plain output: ', plain)
    if(isCorrupt == True):
        print('Message is corrupted')
    else:
        print('Message is received correctly')

    print('\nTesting on a cipher:')
    TestCipher = 'ea066beff0e89e9e3eed2c7dde7bc9501b919423f5d59e35cbe85de03e413c502334c504cc235c981c2ad5f251eee5e6e010884ee8c86fe894eaac8403491787b9ce4ce88bafbc0ab2e90c0c987697b2a5bfb0f64eee930d459a8471ade1486bdb7c37f661f04caf97c47b3deba39ab3f1a866ba3b827baa0668ffc6b4d5ff0879d7223600544284a5'
    TestCipher = binascii.a2b_hex(TestCipher)
    CFBplainTest, isCorrupt = decrypt(TestCipher, key)
    print('CFB plain output: ', CFBplainTest)
    
    
    # a = "aabbc"
    # print(a[:-1])
    # r.seed(753)
    # # for i in range(10):
    # #     print(r.random())
    # a = int(r.random() * 1000000000)
    # s = a.to_bytes(64, 'big')
    # q = 0
    # # print(s)
    # for x in p:
    #     q+=1
    # print(q) 
    # s= "QQQ".encode()+s
    # print(s)
    # # print(s.decode())
    # print(s[:-64])