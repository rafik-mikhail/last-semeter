def factorizeN(n):
    p ,q = 0, 0
    # you can change the initial values depending on your implementation

    # Your Implementation Here
    return p, q
    

def findD(n, e):
    d = 0

    # Your Implementation Here
    return d

def decrypt(n, d, c):
    m = 0

    # Your Implementation Here
    return m



if __name__ == "__main__":
    d = findD(143, 11)
    print("The PrivateKey d is: ", d)
    print("The decrypted message is: ", decrypt(143, d, 106))

    d1 = findD(91, 5)
    print("The PrivateKey d is: ", d1)
    print("The decrypted message is: ", decrypt(91, d1, 9))

    d2 = findD(391, 15)
    print("The PrivateKey d is: ", d2)
    print("The decrypted message is: ", decrypt(391, d2, 364))
