import array as arr
import numpy as np


def generateIC(coset):
    cosetIC = 0
    for c in coset:
        c = c.lower()
        count = [0]*26
        n = 0
        for i in range(0, len(c)):
            val = ord(c[i]) - ord('a')
            if(val >= 0 and val <= 25):
                count[val] = count[val] + 1
                n += 1
        total = 0.0
        for i in range(0, len(count)):
            total += count[i] * (count[i]-1)
        total = total/(n*(n-1))
        cosetIC += total
    cosetIC = cosetIC/len(coset)
    return cosetIC
    
def crack(cipher):
#     cipher='chjdshcjkshdks'
    newString= []
    for i in range (1,11):
        small=['']*i
        for j in range(0, len(cipher)):
            small[(j%i)]+= ''+ cipher[j]
        newString.append(small)
#     print(newString)
    nour = 0
    keyLen=-10
    for i in range(len(newString)):
        k=generateIC(newString[i])
        if k > nour:
            nour =k
            keyLen=i+1 #Because we start from index zero.
    print(keyLen)

if __name__ == "__main__":
    s0 = "RSTCSJLSLRSLFELGWLFIISIKRMGL"
    s1 = "OICPWZXZEVLGCLNFSYPGALPXWZJTEGALPCSIIWDHOIECCBFWPAHOPCGALPCCBROASNWTYHOIDBIHVPSCSIDEVLSYPGDLZDSLXSTBNWOTTMICPBAPJEVLCLCSUSEQCUHZQFBPPDOUHESSFLLGSUSCPGWINETVVESSZXLEIZUFZMVYNLBXYZESALPXRPWLRFLIHTHOXSPANPZCWMCZCJPPTQMALPXOISFEHOIZYZFXSTBNCZFQHRYZHKSTDWNRZCSALPXPLGLFGLXSPMJLLYULXSTBNWESSFTFDVALPSITEYCOJIQZFDECOOUHHSWSIDZALQLJGLIESSTEDEVLGCLNFSYPGDIDPSNIYTIZFPNOBWPEVLTPZDSIHSCHVPNFHDJPBVYRSHVXSTBRXSPMJEYNVHRRPHOIHZFSHLCSALPZBLWHSCKS"
    s2 = "VVVXSQWPSNJMUMJOKKLGFQAVEXAHWRVTMFXVVRKAJTVMFLOPHYWJDSTXKAGFVVTPHKYEPPJOKPSWACJVSIGGVOLXLVMQPVCMGOGMFLAKENVRMIUAKTKVHIXCFJZRAHWFHLIUMHCIRFWGFOETIUNEQVJWEHOSJWVQFYWKYMPGQHWISOPKHYFYV"
    
    crack(s0)
    crack(s1)
    crack(s2)