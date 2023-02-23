class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        p_dict = {}
        p_dict = Counter(p)    
        s_dict = {}
        n = len(p)
        l = 0
        arr = []
        if len(p) > len(s):
            return []
        for r in range(len(s)):

                
            if s[r] not in s_dict:
                s_dict[s[r]] = 1
            else:
                s_dict[s[r]] += 1
            
            if (r - l + 1) == n and s_dict == p_dict:
                arr.append(l)
                

            if (r - l + 1) == n:
                s_dict[s[l]] -= 1
                if s_dict[s[l]] == 0:
                    del s_dict[s[l]]
                l += 1
    
            
        return arr
